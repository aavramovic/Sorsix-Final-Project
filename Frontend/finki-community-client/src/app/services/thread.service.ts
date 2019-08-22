import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {map} from 'rxjs/operators';
import {API_URL, COURSE_LIST, THREAD_LIST, THREAD_LIST_10, THREAD_REPLIES} from '../Models/global-const-url-paths';
import {IClickedCourse} from '../Models/Interfaces/IClickedCourse';

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
        return this.http.get<IThread[]>(API_URL + THREAD_REPLIES + postId).pipe(
            map(threads => this.mapIThreadsToThreads(threads)));
    }

    mapIThreadsToThreads(threads: IThread[]): Thread[] {
        let tempThreads: Thread[] = [];
        threads.forEach(thread => {
            tempThreads.push(new Thread(
                thread.postId,
                thread.username,
                thread.courseName,
                new Date(thread.timeOfPost),
                thread.noOfLikes,
                thread.noOfComments,
                thread.content,
                'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_1280.png',
                thread.title
            ));
        });
        return tempThreads;
    }
}
