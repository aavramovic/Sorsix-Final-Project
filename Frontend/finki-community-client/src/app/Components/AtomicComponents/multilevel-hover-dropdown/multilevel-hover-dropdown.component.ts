import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../../../Models/Classes/Course';
import {Program} from '../../../Models/Enumeration/Program';

@Component({
    selector: 'app-multilevel-hover-dropdown',
    templateUrl: './multilevel-hover-dropdown.component.html',
    styleUrls: ['./multilevel-hover-dropdown.component.css']
})
export class MultilevelHoverDropdownComponent implements OnInit {
    @Input()
    courses: Course[];
    programs = Object.keys(Program).splice(Object.keys(Program).length / 2);

    constructor() {
    }

    ngOnInit() {
    }

    getSubjectsByYearAndProgram(yearOfStudy: number, program: Program): Course[] {
        return this.courses.filter(course => (course.yearOfStudy === yearOfStudy && course.program.includes(program)));
    }

    get Program() {
        return Program;
    };
}
