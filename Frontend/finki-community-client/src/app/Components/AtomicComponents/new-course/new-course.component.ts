import {Component, Inject, OnInit} from '@angular/core';
import {EnumService} from '../../../services/enum.service';
import {MAT_DIALOG_DATA, MatCheckboxChange, MatDialogRef} from '@angular/material';
import {AdminComponent, NewCourseDialogData} from '../../Views/admin/admin.component';
import {FormArray, FormControl, FormGroup, Validators} from '@angular/forms';
import {CourseService} from '../../../services/course.service';

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

    postCourseForm = new FormGroup({
        programs: new FormGroup({}, Validators.required),
        year: new FormControl('', Validators.required),
        semester: new FormControl('', Validators.required),
        courseType: new FormControl('', Validators.required),
        courseDescription: new FormControl(''),
        courseName: new FormControl('')

    });
    programsChecked: Set<string> = new Set<string>();

    constructor(
        public dialogRef: MatDialogRef<AdminComponent>, private courseService: CourseService) {
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    ngOnInit() {
        this.programs.forEach(item => (<FormGroup> this.postCourseForm.get('programs')).addControl(item, new FormControl()));
    }


    onSubmit() {
        // (<Map<string, string>> this.postCourseForm.get('programs').value).forEach((key, value) => value ? console.log(key) : '');
        this.programs.forEach(item => console.log((<FormGroup> this.postCourseForm.get('programs')).get(item).value));

        console.log(this.postCourseForm.get('programs').value);
        this.courseService.postCourse(this.postCourseForm);
    }
}
