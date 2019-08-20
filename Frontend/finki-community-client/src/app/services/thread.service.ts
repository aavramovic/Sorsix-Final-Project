import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {map} from 'rxjs/operators';
import {API_URL, COURSE_LIST, THREAD_LIST, THREAD_LIST_10, THREAD_REPLIES} from '../Models/Classes/GlobalPathStaticVariables';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    getTop10Posts(): Observable<Thread[]> {

        return this.http.get<Thread[]>(API_URL + THREAD_LIST_10);
    }

    //TODO-SERVICE:: /forum/posts/null/:number || /forum/posts/:number
    // vrakja top postovi od site kursevi


    getTopNThreadsByCourse(courseName?: string): Observable<Thread[]> {
        if (!courseName) {
            this.getTop10Posts();
        } else {
            return this.http.get<IThread[]>(API_URL + 'courses/clicked?courseName=' +  courseName).pipe(
                map(threads => this.mapIThreadsToThreads(threads)));
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
