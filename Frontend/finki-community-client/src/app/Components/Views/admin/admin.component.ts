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
    programs: string[] = EnumService.getPrograms();
    years: string[] = EnumService.getYears();
    semesters: string[] = EnumService.getSemesters();
    types: string[] = EnumService.getTypes();

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
            programs: this.programs,
            years: this.years,
            semesters: this.semesters,
            types: this.types
        };

        const dialogRef = this.dialog.open(NewCourseComponent, dialogConfig.data);

        dialogRef.afterClosed().subscribe(
            data => console.log(data)
        );
    }
}
