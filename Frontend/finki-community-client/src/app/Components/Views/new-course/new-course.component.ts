import {Component, Inject, OnInit} from '@angular/core';
import {EnumService} from '../../../services/enum.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {AdminComponent, NewCourseDialogData} from '../admin/admin.component';

@Component({
    selector: 'app-new-course',
    templateUrl: './new-course.component.html',
    styleUrls: ['./new-course.component.css']
})
export class NewCourseComponent implements OnInit {
    programs: string[] = EnumService.getPrograms();
    years: string[] = EnumService.getYears();
    semesters: string[] = EnumService.getSemesters();
    types: string[] = EnumService.getTypes();

    constructor(
        public dialogRef: MatDialogRef<AdminComponent>) {
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    ngOnInit() {
    }

}
