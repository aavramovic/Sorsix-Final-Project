import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {UserService} from '../../../services/user.service';
import {CourseService} from '../../../services/course.service';

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

    constructor(
        private threadService: ThreadService,
        private router: Router,
        private courseService: CourseService) {

    }

    ngOnInit() {
        this.replie$.pipe(switchMap(() =>
            this.threadService.getReplies(this.thread.threadId)
        )).subscribe(replies => this.replies = replies);
    }

    loadComments() {
        this.isOpen = true;
        console.log(this.thread.threadId);
        this.replie$.next();
    }
}
