import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {User} from '../../../Models/Classes/User';
import {Course} from '../../../Models/Classes/Course';
import {UserService} from '../../../services/user.service';
import {CourseService} from '../../../services/course.service';
import {ThreadService} from '../../../services/thread.service';

@Component({
    selector: 'app-thread',
    templateUrl: './thread.component.html',
    styleUrls: ['./thread.component.css']
})
export class ThreadComponent implements OnInit {
    @Input()
    private thread: Thread;
    private user: User; //treba ili preku konstruktor ili na init spored user id i course id od postot da se izvadat
    private course: Course;

    constructor(
        private userService: UserService,
        private courseService: CourseService) {

    }

    ngOnInit() {
        this.userService.getMockUserByUserId(this.thread.userId)
            .subscribe(user => this.user = user);
        this.courseService.getMockCourseByCourseId(this.thread.courseId)
            .subscribe(course => this.course = course);


        // this.userService.getUserByUserId(this.thread.userId)
        //     .subscribe(user => this.user = user);
        // this.courseService.getCourseByCourseId(this.thread.courseId)
        //     .subscribe(course => this.course = course);
    }
}
