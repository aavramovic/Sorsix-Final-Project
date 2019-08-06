import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {}

  getTop10Posts(): Observable<any> {
    return this.http.get('//localhost:8080/top-posts');
  }
}
