import {Component, OnInit} from '@angular/core';
import {CourseService} from '../../../services/course.service';
import {Course} from '../../../Models/Classes/Course';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';

@Component({
    selector: 'app-courses',
    templateUrl: './course-bar.component.html',
    styleUrls: ['./course-bar.component.css']
})
export class CourseBarComponent implements OnInit {
    courses: Course[];

    constructor(private courseService: CourseService,
                private mock: MockClassesCreationService) {
    }

    ngOnInit() {
        //TODO:// trgni go delayot
        this.mock.delay(2000).then(() =>
            this.courseService.getMockCourses().subscribe(courses => this.courses = courses));
        // this.courseService.getMockCourses().subscribe(courses => this.courses = courses));
    }

}

