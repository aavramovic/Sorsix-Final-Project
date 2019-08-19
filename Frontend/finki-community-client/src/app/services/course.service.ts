import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';
import {GlobalPathStaticVariables} from '../Models/Classes/GlobalPathStaticVariables';
import {ICourse} from '../Models/Interfaces/ICourse';
import {map} from 'rxjs/operators';
import {YearOfStudy} from '../Models/Enumeration/YearOfStudy';
import {Semester} from '../Models/Enumeration/Semester';

@Injectable({
    providedIn: 'root'
})
export class CourseService {
    readonly _API_URL: string = GlobalPathStaticVariables.API_URL;
    readonly _COURSE_LIST: string = GlobalPathStaticVariables.COURSE_LIST;

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }


    getCourses<T>(): Observable<Course[]> {
        return this.http.get<ICourse[]>(this._API_URL + this._COURSE_LIST).pipe(
            map(
                courses => {
                    let tempCourses: Course[] = [];
                    courses.forEach(course => {
                        tempCourses.push(new Course(
                            course.courseName,
                            course.courseId,
                            course.code,
                            course.courseDescription,
                            YearOfStudy[course.studyYear],
                            [Program[course.program]],
                            Semester[course.semester],
                            Type[course.courseType]
                        ));
                    });
                    return tempCourses;
                }
            )
        );

    }

    getCourseByCourseId(courseId: number): Observable<Course> {
        return this.http.get<Course>(this._API_URL + this._COURSE_LIST + courseId);
    }

    getCoursesByProperties(year?: number, program?: string, mandatory?: boolean) {
        return this.http;
    }
}
