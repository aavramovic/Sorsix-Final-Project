import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';
import {ICourse} from '../Models/Interfaces/ICourse';
import {map} from 'rxjs/operators';
import {getKeyByValue, YearOfStudy} from '../Models/Enumeration/YearOfStudy';
import {Semester} from '../Models/Enumeration/Semester';
import {API_URL, COURSE_LIST} from '../Models/Classes/GlobalPathStaticVariables';

@Injectable({
    providedIn: 'root'
})
export class CourseService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }


    getCourses<T>(program?: string, yearOfStudy?: string, semester?: string, type?: string): Observable<Course[]> {
        let queryString: string = '?';


        let programString = program ? 'program=' + program : '';
        let yearString = yearOfStudy ? 'studyYear=' + getKeyByValue(yearOfStudy) : '';
        let semesterString = semester ? 'semester=' + semester : '';
        let typeString = type ? 'type=' + type : '';

        let array = [programString, yearString, semesterString, typeString];
        array = array.filter(item => item.length != 0);
        queryString += array.join('&');
        console.log(queryString);
        getKeyByValue(yearOfStudy);


        return this.http.get<ICourse[]>(API_URL + COURSE_LIST + queryString).pipe(
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
        return this.http.get<Course>(API_URL + COURSE_LIST + courseId);
    }

    getCoursesByProperties(year?: number, program?: string, mandatory?: boolean) {
        return this.http;
    }
}
