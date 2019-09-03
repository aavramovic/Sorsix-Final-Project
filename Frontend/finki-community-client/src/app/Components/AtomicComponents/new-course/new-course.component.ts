import {Component, OnInit} from '@angular/core';
import {EnumService} from '../../../services/enum.service';
import {MatDialogRef} from '@angular/material';
import {AdminComponent} from '../../Views/admin/admin.component';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
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
        code: new FormControl('', Validators.required),
        year: new FormControl('', Validators.required),
        semester: new FormControl('', Validators.required),
        courseType: new FormControl('', Validators.required),
        courseDescription: new FormControl('', Validators.required),
        courseName: new FormControl('', Validators.required),
        programs: new FormGroup({}, Validators.required)
    });


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

        let message = this.courseService.postCourse(this.postCourseForm);
        this.dialogRef.close();
    }

    close() {
        this.dialogRef.close();
    }

    getErrorMessage(value: string) {
        let errors: string[] = [];

        if (this.postCourseForm.get(value).hasError('required')) {
            errors.push('You must enter a value');
        }
        if (this.postCourseForm.get(value).hasError('notEquivalent')) {
            errors.push('Does not match existing courses');
        }

        return errors.join(', ');
    }

}
