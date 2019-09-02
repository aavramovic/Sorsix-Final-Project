import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {ThreadService} from '../../../services/thread.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {ThreadBarComponent} from '../../SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {Subject} from 'rxjs';
import {Course} from '../../../Models/Classes/Course';
import {CourseService} from '../../../services/course.service';
import {switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
    selector: 'app-new-reply',
    templateUrl: './new-reply.component.html',
    styleUrls: ['./new-reply.component.css']
})
export class NewReplyComponent implements OnInit {
    postPostForm = new FormGroup({
        content: new FormControl('', [Validators.required, Validators.maxLength(300)]),
        username: new FormControl('', Validators.required),
        title: new FormControl('', Validators.required),
        replyToPostId: new FormControl('')
    });


    constructor(private threadService: ThreadService,
                public dialogRef: MatDialogRef<ThreadBarComponent>,
                private courseService: CourseService,
                private route: Router,
                @Inject(MAT_DIALOG_DATA) public data: any) {
    }


    ngOnInit() {
        this.postPostForm.get('username').setValue(localStorage.getItem('username'));
        this.postPostForm.get('replyToPostId').setValue(this.data.postId);

    }

    onSubmit() {
        console.log(this.postPostForm.get('content').value);
        console.log(this.postPostForm.get('username').value);
        console.log(this.postPostForm.get('title').value);
        console.log(this.postPostForm.get('replyToPostId').value);

        this.threadService.postThread(this.postPostForm);
        this.route.navigate(['/start']).then(r => {
        });
        this.dialogRef.close();
    }

    close() {
        this.dialogRef.close();
    }

    getErrorMessage(value: string) {
        let errors: string[] = [];

        if (this.postPostForm.get(value).hasError('required')) {
            errors.push('You must enter a value');
        }
        if (this.postPostForm.get(value).hasError('notEquivalent')) {
            errors.push('Does not match existing courses');
        }

        return errors.join(', ');
    }
}
