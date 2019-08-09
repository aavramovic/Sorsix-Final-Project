import {Injectable} from '@angular/core';
import {Course} from '../Models/Classes/Course';
import {Observable, of} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class MockClassesCreationService {

    constructor() {
    }

    getMockCourses(): Observable<Set<Course>> {
        let course: Set<Course> = new Set<Course>();
        course.add(this.getMockCourse('Калкулус'));
        course.add(this.getMockCourse('Дискретна Математика'));
        course.add(this.getMockCourse('Професионални Вештини'));
        course.add(this.getMockCourse('Структурно Програмирање'));
        course.add(this.getMockCourse('Објектно Програмирање'));
        return of(course);
    }

    getMockCourse(name: string): Course {
        return new Course(name, Math.random(), 'Code', 'Description');
    }
}
