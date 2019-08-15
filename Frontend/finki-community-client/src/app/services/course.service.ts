import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';

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

    getCoursesByProperties(year?: number, program?: string, mandatory?: boolean) {
        return this.http;
    }

    /**
     * MOCK METHODS
     * */
    getMockCourses(): Observable<Course[]> {
        return this.mock.getMockCourses();
    }

    getMockCourse(name: string): Course {
        return this.mock.getMockCourse(name, 1, [Program.KNI], Type.Mandatory, "6");
    }

    getMockCourseByCourseId(courseId: string): Observable<Course> {
        return of(this.mock.getMockCourseByCourseId(courseId));
    }
}
