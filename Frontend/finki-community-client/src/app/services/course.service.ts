import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';

@Injectable({
    providedIn: 'root'
})
export class CourseService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    getCourseByCourseId(courseId: string): Observable<Course> {
        return this.http.get<Course>('course/' + courseId);
    }


    getMockCourses(): Observable<Course[]> {
        return this.mock.getMockCourses();
    }

    getMockCourse(name: string): Course {
        return this.mock.getMockCourse(name);
    }

    getMockCourseByCourseId(courseId: string): Observable<Course> {
        return of(this.mock.getMockCourseByCourseId(courseId));
    }
}
