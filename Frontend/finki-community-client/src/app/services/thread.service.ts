import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {map, tap} from 'rxjs/operators';
import {API_URL, THREAD_LIST, THREAD_REPLIES, USER_LIKES_POST, USERS, POST_THREAD} from '../Models/global-const-url-paths';
import {IClickedCourse} from '../Models/Interfaces/IClickedCourse';
import {IGetRepliesByPostId} from '../Models/Interfaces/IGetRepliesByPostId';
import {NewPostLikeRequest} from '../Models/Classes/NewPostLikeRequest';
import {Authorization} from '../Models/Enumeration/Authorization';
import {FormGroup} from '@angular/forms';
import {CourseService} from './course.service';
import {PostThread} from '../Models/Classes/PostThread';
import {IPageResponse} from '../Components/SetsOfAtomicComponents/thread-bar/model/ipage-response';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {

    constructor(private http: HttpClient,
                private courseService: CourseService,
                private mock: MockClassesCreationService) {
    }

    getTopNPosts(numberOfPosts: number): Observable<Thread[]> {
        return this.http.get<IThread[]>(API_URL + THREAD_LIST + numberOfPosts + '&username=' + localStorage.getItem('username')).pipe(
            map(threads => this.mapIThreadsToThreads(threads)),
            tap(threads => console.log(threads))
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
                }));
        }
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
                new Date(thread.timeOfPost),
                thread.noOfLikes,
                thread.noOfComments,
                thread.content,
                thread.title,
                thread.username,
                thread.sex,
                Authorization[thread.role],
                thread.isLiked,
                thread.sex == 'M' ? 'MALE_AVATAR.PNG' : 'FEMALE_AVATAR.PNG'
            ));
        });
        return tempThreads;
    }

    likes(username: string, thread: Thread) {
        // console.log('LIKE clicked');
        // console.log('Username:', username);
        // console.log('Thread:', thread.threadId);
        return this.http
            .post<NewPostLikeRequest>(
                API_URL + USERS + USER_LIKES_POST,
                new NewPostLikeRequest(username, thread.threadId),
                {
                    headers: new HttpHeaders(
                        {
                            'Content-Type': 'application/json'
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
        let threadIdString = postPostForm.get('replyToPostId').value;
        let postThread: PostThread = new PostThread(
            postPostForm.get('content').value,
            postPostForm.get('title').value,
            postPostForm.get('courseName').value,
            postPostForm.get('username').value,
            threadIdString ? parseInt(threadIdString) : null);
        // console.log(postThread);
        this.http.post(API_URL + POST_THREAD, postThread).subscribe(
            response => console.log(response),
            error => console.log('ERROR: ' + error.message)
        );
    }
}
