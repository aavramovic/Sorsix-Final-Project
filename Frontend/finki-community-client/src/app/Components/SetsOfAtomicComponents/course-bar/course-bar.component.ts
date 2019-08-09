import {Component, Input, OnInit} from '@angular/core';
import {CourseService} from '../../../services/course.service';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-courses',
    templateUrl: './course-bar.component.html',
    styleUrls: ['./course-bar.component.css']
})
export class CourseBarComponent implements OnInit {
    courses: Set<Course>;
    mockCourse: Course;

    constructor(private courseService: CourseService) {
        this.courses = new Set<Course>();
    }

    ngOnInit() {
        this.courseService.getMockCourses().subscribe(courses => this.courses = courses);
        this.mockCourse = this.courseService.getMockCourse('Tocak');
    }

}
