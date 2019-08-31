import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {IThread} from '../Models/Interfaces/IThread';
import {map, tap} from 'rxjs/operators';
import {API_URL, THREAD_LIST, THREAD_REPLIES, USER_LIKES_POST, USERS} from '../Models/global-const-url-paths';
import {IClickedCourse} from '../Models/Interfaces/IClickedCourse';
import {IGetRepliesByPostId} from '../Models/Interfaces/IGetRepliesByPostId';
import {NewPostLikeRequest} from '../Models/Classes/NewPostLikeRequest';
import {Authorization} from '../Models/Enumeration/Authorization';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {

    constructor(private http: HttpClient) {}

    getTopNPosts(numberOfPosts: number): Observable<Thread[]> {
        return this.http.get<IThread[]>(API_URL + THREAD_LIST + numberOfPosts + '&username=' + localStorage.getItem('username')).pipe(
            map(threads => this.mapIThreadsToThreads(threads)),
            tap(threads => console.log(threads))
        );
    }


    getTopNThreadsByCourse(numberOfPosts: number, courseName?: string): Observable<Thread[]> {
        if (courseName.length === 0) {
            return this.getTopNPosts(numberOfPosts);
        } else {

            return this.http.get<IClickedCourse>(API_URL + 'forum/courses/clicked?courseName=' + courseName + '&noOfPosts=' + numberOfPosts)
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
                'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_1280.png',
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
                { headers: new HttpHeaders(
                        {
                            'Content-Type': 'application/json'
                        })
                }
            )
            .subscribe(
                res => {
                    thread.isLiked = !thread.isLiked;
                    if(thread.isLiked === false){
                        thread.numberOfLikes--;
                    } else{
                        thread.numberOfLikes++;
                    }
                }
            );
    }
}
