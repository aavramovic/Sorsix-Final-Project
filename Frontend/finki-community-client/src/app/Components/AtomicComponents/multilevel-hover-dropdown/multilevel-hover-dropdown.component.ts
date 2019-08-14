import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-multilevel-hover-dropdown',
    templateUrl: './multilevel-hover-dropdown.component.html',
    styleUrls: ['./multilevel-hover-dropdown.component.css']
})
export class MultilevelHoverDropdownComponent implements OnInit {
    @Input()
    courses: Course[];

    constructor() {
    }

    ngOnInit() {
    }


}
