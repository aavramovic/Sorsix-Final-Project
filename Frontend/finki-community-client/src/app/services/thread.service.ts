import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of, Subject} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {catchError, map} from 'rxjs/operators';
import {API_URL, THREAD_LIST, THREAD_REPLIES, USER_LIKES_POST, USERS, POST_THREAD} from '../Models/global-const-url-paths';
import {IClickedCourse} from '../Models/Interfaces/IClickedCourse';
import {IGetRepliesByPostId} from '../Models/Interfaces/IGetRepliesByPostId';
import {NewPostLikeRequest} from '../Models/Classes/NewPostLikeRequest';
import {Authorization} from '../Models/Enumeration/Authorization';
import {FormGroup} from '@angular/forms';
import {CourseService} from './course.service';
import {PostThread} from '../Models/Classes/PostThread';
import {IPageResponse} from '../Components/SetsOfAtomicComponents/thread-bar/model/ipage-response';
import {MatSnackBar} from '@angular/material';
import {PostReply} from '../Models/Classes/PostReply';
import {Router} from '@angular/router';
import {UrlService} from './url.service';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {
    invokeEvent: Subject<any> = new Subject();

    constructor(private http: HttpClient,
                private courseService: CourseService,
                private mock: MockClassesCreationService,
                private _snackBar: MatSnackBar,
                private route: Router,
                private url: UrlService
    ) {
    }

    getTopNPosts(numberOfPosts: number): Observable<Thread[]> {
        return this.http.get<IThread[]>(API_URL + THREAD_LIST + numberOfPosts + '&username=' + localStorage.getItem('username')).pipe(
            map(threads => this.mapIThreadsToThreads(threads))
        );
    }

    getThreads(): Observable<IPageResponse> {
        return this.http
            .get<IPageResponse>('http://localhost:8080/forum/posts?username=' + localStorage.getItem('username'));
    }


    getTopNThreadsByCourse(numberOfPosts: number, courseName?: string): Observable<Thread[]> {
        if (courseName.length === 0) {
            return this.getTopNPosts(numberOfPosts);
        } else {

            return this.http.get<IClickedCourse>(API_URL +
                'forum/courses/clicked?courseName=' + courseName +
                '&noOfPosts=' + numberOfPosts + (
                    localStorage.getItem('username') ?
                        '&username=' + localStorage.getItem('username') :
                        ''))
                .pipe(map(course => {
                    return this.mapIThreadsToThreads(course.posts);
                }), catchError(this.handleError));
        }
    }

    // noinspection JSMethodCanBeStatic
    private handleError(error): Observable<Thread[]> {
        this.openSnackBar('error');
        return of([]);
    }

    getReplies(postId: number): Observable<Thread[]> {
        return this.http.get<IGetRepliesByPostId>(API_URL + THREAD_REPLIES + '?postId=' + postId + '&username=' + localStorage.getItem('username')).pipe(
            map(reply => this.mapIThreadsToThreads(reply.replies)));
    }

    mapIThreadsToThreads(threads: IThread[]): Thread[] {
        let tempThreads: Thread[] = [];
        threads.forEach(thread => {
            tempThreads.push(new Thread(
                thread.id,
                thread.courseName,
                thread.courseCode,
                new Date(thread.timeOfPost),
                thread.noOfLikes,
                thread.noOfComments,
                thread.content,
                thread.title,
                thread.username,
                thread.sex,
                Authorization[thread.role],
                thread.isLiked,
                thread.sex === 'M' ? 'MALE_AVATAR.PNG' : 'FEMALE_AVATAR.PNG',
                thread.repliedTo
            ));
        });
        return tempThreads;
    }

    likes(username: string, thread: Thread) {
        return this.http
            .post<NewPostLikeRequest>(
                API_URL + USERS + USER_LIKES_POST,
                new NewPostLikeRequest(username, thread.threadId),
                {
                    headers: new HttpHeaders(
                        {
                            'Content-Type': 'application/json',
                            'Authorization': 'Bearer ' + localStorage.getItem('id_token')
                        })
                }
            )
            .subscribe(
                res => {
                    thread.isLiked = !thread.isLiked;
                    if (thread.isLiked === false) {
                        thread.numberOfLikes--;
                    } else {
                        thread.numberOfLikes++;
                    }
                }
            );
    }

    postThread(postPostForm: FormGroup) {
        let content = postPostForm.get('content').value;
        let title = postPostForm.get('title').value;
        let username = postPostForm.get('username').value;
        let courseName = postPostForm.get('courseName') ? postPostForm.get('courseName').value : null;
        let threadIdString = postPostForm.get('replyToPostId') ? parseInt(postPostForm.get('replyToPostId').value) : null;

        let post;
        if (threadIdString !== null) {
            post = new PostReply(content, title, username, threadIdString);
        } else if (courseName !== null) {
            post = new PostThread(content, title, username, courseName);
        } else {
            this.openSnackBar('Error with the post');
        }
        this.http.post(
            API_URL + POST_THREAD,
            post,
            {headers: new HttpHeaders({
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('id_token')
                })}
            )
            .subscribe(
            response => {
                this.openSnackBar('Thread posted');
                this.invokeEvent.next();
            },
            error => this.openSnackBar('An error has occurred please try again')
        );


    }

    openSnackBar(message: string) {
        this._snackBar.open(message, 'Close', {
            duration: 3000,
        });
    }
}
