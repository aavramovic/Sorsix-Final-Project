import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Thread} from '../Models/Interfaces/Thread';
import {GlobalPathStaticVariables} from '../Models/Classes/GlobalPathStaticVariables';

@Injectable({
    providedIn: 'root'
})
export class ThreadService {
    readonly _API_URL: string = GlobalPathStaticVariables.API_URL;
    readonly _TOP_POSTS_URL: string = GlobalPathStaticVariables.TOP_POSTS_URL;
    constructor(private http: HttpClient) {
    }

    getTop10Posts(): Observable<Thread []> {
        return this.http.get<Thread []>(this._API_URL+this._TOP_POSTS_URL);
    }

    getTopNPosts(numberOfThreadsToShow: number): Observable<Thread> {
        return this.http.get<Thread>(this._API_URL + this._TOP_POSTS_URL + '/' + numberOfThreadsToShow);
    }
}
