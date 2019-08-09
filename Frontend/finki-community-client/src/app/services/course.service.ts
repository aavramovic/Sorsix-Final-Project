import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) { }

  getCourseByCourseId(courseId: number): Observable<Course> {
    return this.http.get<Course>("curse/id");
  }
}
