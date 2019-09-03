import {Component, HostListener, Input, OnInit} from '@angular/core';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-course',
    templateUrl: './course.component.html',
    styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
    @Input()
    private course: Course;

    programs;

    @HostListener('click') onClick() {
    }

    constructor() {
    }

    ngOnInit() {
        this.programs = this.course.program.keys();
    }


}
