import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {GlobalPathStaticVariables} from '../Models/Classes/GlobalPathStaticVariables';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {IThread} from '../Models/Interfaces/IThread';
import {map} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {
    readonly _API_URL: string = GlobalPathStaticVariables.API_URL;
    readonly _THREAD_LIST: string = GlobalPathStaticVariables.THREAD_LIST;

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    getTopNPosts(numberOfThreadsToShow: number): Observable<Thread> {
        return this.http.get<Thread>(this._API_URL + this._THREAD_LIST + '/' + numberOfThreadsToShow);
    }

    //TODO-SERVICE:: /forum/posts/null/:number || /forum/posts/:number
    // vrakja top postovi od site kursevi

    getMockThreads(): Observable<Thread[]> {
        return this.mock.getMockThreads();
    }

    getMockThread(content: string): Thread {
        return this.mock.getMockThread(content, '5');
    }

    getTopNThreadsByCourse(numberOfThreadsByPage: number, courseId: number): Observable<Thread[]> {
        return this.http.get<IThread[]>(this._API_URL + this._THREAD_LIST + courseId + '/' + numberOfThreadsByPage).pipe(
            map(
                threads => {
                    let tempThreads: Thread[] = [];
                    threads.forEach(thread => {
                        tempThreads.push(new Thread(
                            thread.postId,
                            thread.userId,
                            thread.courseId,
                            new Date(thread.timestamp),
                            thread.content,
                            thread.title
                        ));
                    });
                    return tempThreads;
                }
            )
        );
    }
}
