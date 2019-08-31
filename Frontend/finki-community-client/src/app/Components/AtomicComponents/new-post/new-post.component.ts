import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ThreadService} from '../../../services/thread.service';
import {MatDialogRef} from '@angular/material';
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
        username: new FormControl('', Validators.required)
    });

    username = localStorage.getItem('username');


    constructor(private threadService: ThreadService,
                private courseService: CourseService,
                private userService: UserService,
                public dialogRef: MatDialogRef<ThreadBarComponent>) {
    }


    ngOnInit() {

    }

    onSubmit() {
        // console.log(this.postCourseForm.get('programs').value);


        this.threadService.postThread(this.postPostForm);
    }

    close() {
        this.dialogRef.close();
    }
}
