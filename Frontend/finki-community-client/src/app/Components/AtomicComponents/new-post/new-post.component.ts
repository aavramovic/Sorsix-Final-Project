import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ThreadService} from '../../../services/thread.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {AdminComponent} from '../../Views/admin/admin.component';
import {ThreadBarComponent} from '../../SetsOfAtomicComponents/thread-bar/thread-bar.component';
import {CourseService} from '../../../services/course.service';
import {UserService} from '../../../services/user.service';

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
        replyToPostId: new FormControl('')
    });



    constructor(private threadService: ThreadService,
                private courseService: CourseService,
                private userService: UserService,
                public dialogRef: MatDialogRef<ThreadBarComponent>,
                @Inject(MAT_DIALOG_DATA) public data: any) {
    }


    ngOnInit() {
        this.postPostForm.get('username').setValue(localStorage.getItem('username'));
        if(this.data.postId){
            this.postPostForm.get('replyToPostId').setValue(this.data.postId);
        }
    }

    onSubmit() {
        // console.log(this.postCourseForm.get('programs').value);
        this.threadService.postThread(this.postPostForm);
    }

    close() {
        this.dialogRef.close();
    }
}
