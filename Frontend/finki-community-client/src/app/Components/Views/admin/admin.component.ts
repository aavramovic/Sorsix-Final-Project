import {Component, OnInit} from '@angular/core';
import {EnumService} from '../../../services/enum.service';
import {NewCourseComponent} from '../../AtomicComponents/new-course/new-course.component';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';

export interface NewCourseDialogData {
    programs: string[];
    year: string[];
    semester: string[];
    type: string[];

}

@Component({
    selector: 'app-admin',
    templateUrl: './admin.component.html',
    styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
    program: string;
    year: string;
    semester: string;
    type: string;

    constructor(public dialog: MatDialog) {
    }

    ngOnInit() {
    }

    openDialog(): void {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = true;
        dialogConfig.data = {
            programs: [],
            years: [],
            semesters: [],
            types: []
        };

        //We don't return data back from the modal components instead they communicate themselves
        //Maybe let it return a boolean that tells us
        this.dialog.open(NewCourseComponent, dialogConfig.data);

    }
}
