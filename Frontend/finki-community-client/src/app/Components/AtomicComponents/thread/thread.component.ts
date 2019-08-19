import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {User} from '../../../Models/Classes/User';
import {Course} from '../../../Models/Classes/Course';
import {UserService} from '../../../services/user.service';
import {CourseService} from '../../../services/course.service';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-thread',
    templateUrl: './thread.component.html',
    styleUrls: ['./thread.component.css']
})
export class ThreadComponent implements OnInit {
    @Input()
    private thread: Thread;
    replie$ = new Subject<Thread[]>();
    replies: Thread[];

    isOpen: boolean = false;

    //TODO:// zavisi od backendot i databazata namesto ovie dve propery i onInit da se prakjaat ushte dva requesta da bide se vo thread elementot
    private user: User; //treba ili preku konstruktor ili na init spored user id i course id od postot da se izvadat
    private course: Course;


    constructor(
        private userService: UserService,
        private courseService: CourseService,
        private threadService: ThreadService) {

    }

    ngOnInit() {
    }

    loadComments() {
        this.replie$.pipe(switchMap(() =>
            this.threadService.getReplies(this.thread.threadId)
        )).subscribe(replies => this.replies = replies);
    }
}
