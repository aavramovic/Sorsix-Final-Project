import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
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

    getCourseByCourseId(courseId: number): Observable<Course> {
        return this.http.get<Course>('course/id');
    }

    getMockCourses(): Observable<Set<Course>> {
        return this.mock.getMockCourses();
    }

    getMockCourse(name: string) {
        return this.mock.getMockCourse(name);
    }
}
