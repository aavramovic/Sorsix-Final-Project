import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Course} from '../Models/Classes/Course';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';
import {ICourse} from '../Models/Interfaces/ICourse';
import {catchError, map} from 'rxjs/operators';
import {getKeyByValue, YearOfStudy} from '../Models/Enumeration/YearOfStudy';
import {Semester} from '../Models/Enumeration/Semester';
import {API_URL, COURSE_LIST, POST_COURSE} from '../Models/global-const-url-paths';
import {IPostCourse} from '../Models/Interfaces/IPostCourse';
import {PostCourse} from '../Models/Classes/PostCourse';
import {Router} from '@angular/router';


@Injectable({
    providedIn: 'root'
})
export class CourseService {

    constructor(private http: HttpClient,
                private router: Router) {
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

    postCourse(courseName: string, courseDescription: string, programs: Program[], studyYear: YearOfStudy, semester: Semester, courseType: Type)/*:Observable<ICourse>*/ {
        let postRequest: PostCourse = new PostCourse(courseName, courseDescription, courseType, programs, semester, studyYear);

        // return this.http.post<IPostCourse>(API_URL+POST_COURSE,postRequest, httpOptions)
        //     .pipe(
        //         catchError(this.handleError('addCourse', postRequest))
        //     )
    }


    private handleError(addCourse: string, postRequest: PostCourse) {
        return function(p1: any, p2: Observable<unknown>) {
            return undefined;
        };
    }


}
