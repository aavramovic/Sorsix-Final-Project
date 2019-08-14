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
        // console.log('User Click using Host Listener: ' + this.course.courseName);
    }

    constructor() {
    }

    ngOnInit() {
        this.programs = this.course.program.keys();
        // console.log(this.course.program);
    }


}
