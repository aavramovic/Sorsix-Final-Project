import {Component, OnInit} from '@angular/core';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-main-view',
    templateUrl: './main-view.component.html',
    styleUrls: ['./main-view.component.css']
})
export class MainViewComponent implements OnInit {
    selectedCourse: Course;

    constructor() {
    }

    ngOnInit() {
    }

    setSelectedCourses(course: Course) {
        this.selectedCourse = course;
    }
}