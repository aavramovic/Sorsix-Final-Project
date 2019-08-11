import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-course',
    templateUrl: './course.component.html',
    styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
    @Input()
    private course: Course;

    constructor() {
    }

    ngOnInit() {
    }

}
