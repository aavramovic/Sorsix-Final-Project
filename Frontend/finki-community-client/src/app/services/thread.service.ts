import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Thread} from '../Models/Interfaces/Thread';

@Injectable({
  providedIn: 'root'
})
export class ThreadService {
  private originUrl = '/localhost:8080';
  private topPostsUrl = '/top-posts';// Mozebi ke ima /{number}

  constructor(private http: HttpClient) {
  }

  getTop10Posts(): Observable<Thread> {
    return this.http.get(this.originUrl + this.topPostsUrl);
  }

  getTopNPosts(numberOfThreadsToShow: number): Observable<Thread>{
    return this.http.get(this.originUrl + this.topPostsUrl+"/"+numberOfThreadsToShow);
  }
}
