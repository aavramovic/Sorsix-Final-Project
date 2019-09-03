import {Component, OnInit} from '@angular/core';
import {EnumService} from '../../../services/enum.service';
import {NewCourseComponent} from '../../AtomicComponents/new-course/new-course.component';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material';

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

    constructor(public dialog: MatDialog,
                private _snackBar: MatSnackBar) {
    }

    ngOnInit() {
    }

    openDialog(): void {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.disableClose = true;
        dialogConfig.maxHeight = 'fit-content';
        this.dialog.open(NewCourseComponent, dialogConfig);

    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action, {
            duration: 2000,
        });
    }
}
