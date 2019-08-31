import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';
import {Authorization} from '../../../Models/Enumeration/Authorization';

@Component({
    selector: 'app-thread',
    templateUrl: './thread.component.html',
    styleUrls: ['./thread.component.css']
})
export class ThreadComponent implements OnInit {
    @Input() private thread: Thread;
    replie$ = new Subject<Thread[]>();
    replies: Thread[];
    isLoggedIn: boolean = false;
    isOpen: boolean = false;
    role: string;
    admin: Authorization = Authorization.ADMIN;
    moderator: Authorization = Authorization.MODERATOR;
    user: Authorization = Authorization.USER;

    constructor(
        private threadService: ThreadService,
        private router: Router,
        private authService: AuthenticationService) {

    }

    ngOnInit() {
        this.isLoggedIn = AuthenticationService.isLoggedIn();
        if (this.isLoggedIn) {
            this.role = localStorage.getItem('role');
        }

        this.replie$.pipe(switchMap(() =>
            this.threadService.getReplies(this.thread.threadId)
        )).subscribe(replies => this.replies = replies);
        this.authService.isLoggedIn$.subscribe(r => {
            this.isLoggedIn = r;
            if (this.isLoggedIn) {
                this.role = localStorage.getItem('role');
            }
        });

    }

    loadComments() {
        this.isOpen = true;
        console.log(this.thread.threadId);
        this.replie$.next();
    }

    likes(thread: Thread) {
        this.threadService.likes(localStorage.getItem('username'), thread);
    }
}
