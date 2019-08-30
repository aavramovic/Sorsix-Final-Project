import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {map} from 'rxjs/operators';
import {API_URL, COURSE_LIST, THREAD_LIST, THREAD_LIST_10, THREAD_REPLIES, USER_LIKES_POST, USERS} from '../Models/global-const-url-paths';
import {IClickedCourse} from '../Models/Interfaces/IClickedCourse';
import {IGetRepliesByPostId} from '../Models/Interfaces/IGetRepliesByPostId';
import {NewPostLikeRequest} from '../Models/Classes/NewPostLikeRequest';
import {Authorization} from '../Models/Enumeration/Authorization';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    getTopNPosts(numberOfPosts: number): Observable<Thread[]> {
        return this.http.get<IThread[]>(API_URL + THREAD_LIST + numberOfPosts).pipe(
            map(threads => this.mapIThreadsToThreads(threads))
        );
    }


    getTopNThreadsByCourse(numberOfPosts: number, courseName?: string): Observable<Thread[]> {
        if (courseName.length == 0) {
            return this.getTopNPosts(numberOfPosts);
        } else {

            return this.http.get<IClickedCourse>(API_URL + 'forum/courses/clicked?courseName=' + courseName + '&noOfPosts=' + numberOfPosts)
                .pipe(map(course => {
                    return this.mapIThreadsToThreads(course.posts);
                }));
        }
    }

    getReplies(postId: number): Observable<Thread[]> {
        return this.http.get<IGetRepliesByPostId>(API_URL + THREAD_REPLIES + '?postId=' + postId).pipe(
            map(reply => this.mapIThreadsToThreads(reply.replies)));
    }

    mapIThreadsToThreads(threads: IThread[]): Thread[] {
        let tempThreads: Thread[] = [];
        threads.forEach(thread => {
            tempThreads.push(new Thread(
                thread.id,
                thread.username,
                thread.courseName,
                new Date(thread.timeOfPost),
                thread.noOfLikes,
                thread.noOfComments,
                thread.content,
                'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_1280.png',
                thread.title,
                thread.sex,
                Authorization[thread.role]
            ));
        });
        return tempThreads;
    }

    likes(username: string, threadId: number): Observable<NewPostLikeRequest> {
        return this.http.post<NewPostLikeRequest>(API_URL + USERS + USER_LIKES_POST, new NewPostLikeRequest(username, threadId));
    }
}
