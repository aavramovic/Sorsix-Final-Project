import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {ThreadService} from '../../../services/thread.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ThreadBarComponent} from '../../SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {Observable, Subject} from 'rxjs';
import {Course} from '../../../Models/Classes/Course';
import {CourseService} from '../../../services/course.service';
import {map, startWith, switchMap} from 'rxjs/operators';
import {ActivatedRoute, Route, Router, RouterLink} from '@angular/router';

@Component({
    selector: 'app-new-post',
    templateUrl: './new-post.component.html',
    styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
    postPostForm = new FormGroup({
        content: new FormControl('', [Validators.required, Validators.maxLength(300)]),
        courseName: new FormControl('', Validators.required),
        username: new FormControl('', Validators.required),
        title: new FormControl('', Validators.required),
    }, [this.courseValidator('courseName')]);

    private courseValidator(value): ValidatorFn {
        return (group: FormGroup): ValidationErrors => {
            const val = group.get(value);
            if (!val) {
                return;
            }
            if (this.courses && !this.courses.includes(val.value)) {
                val.setErrors({notEquivalent: true});
            } else {
                val.setErrors(null);
            }
            return;
        };
    }

    course$ = new Subject();
    courses: string[] = [];
    filteredCourses: Observable<string[]>;

    constructor(private threadService: ThreadService,
                public dialogRef: MatDialogRef<ThreadBarComponent>,
                private courseService: CourseService,
                private route: Router,
                @Inject(MAT_DIALOG_DATA) public data: any) {
    }


    ngOnInit() {
        this.postPostForm.get('username').setValue(localStorage.getItem('username'));

        this.course$.pipe(switchMap(() =>
            this.courseService.getCourseNames())).subscribe(courses =>
            this.courses = courses
        );

        this.filteredCourses =
            (<FormControl> this.postPostForm.get('courseName'))
                .valueChanges
                .pipe(
                    startWith(''),
                    map(course => course ? this._filterCourses(course) : this.courses.slice()));
        this.course$.next();


    }

    onSubmit() {
        // console.log(this.postPostForm.get('replyToPostId').value);
        this.threadService.postThread(this.postPostForm);
        this.dialogRef.close();
    }

    close() {
        this.dialogRef.close();
    }

    private _filterCourses(value: string) {
        const filterValue = value.toLowerCase();
        return this.courses.filter(course => course.toLowerCase().indexOf(filterValue) === 0);
    }
}
