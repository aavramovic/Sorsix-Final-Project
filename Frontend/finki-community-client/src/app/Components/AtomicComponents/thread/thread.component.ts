import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {switchMap, tap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';
import {Authorization} from '../../../Models/Enumeration/Authorization';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {NewPostComponent} from '../new-post/new-post.component';
import {NewReplyComponent} from '../new-reply/new-reply.component';

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
        public dialog: MatDialog,
        private authService: AuthenticationService) {

    }

    ngOnInit() {
        this.isLoggedIn = AuthenticationService.isLoggedIn();
        if (this.isLoggedIn) {
            this.role = localStorage.getItem('role');
        }

        this.replie$.pipe(
            switchMap(() =>
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
        this.replie$.next();
    }

    likes(thread: Thread) {
        this.threadService.likes(localStorage.getItem('username'), thread);
    }

    openDialog(threadId?: number, courseName?: string): void {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.disableClose = true;
        dialogConfig.height = 'max-content';

        dialogConfig.width = '600px';
        dialogConfig.data = {
            postId: threadId,
        };

        if (threadId) {
            this.dialog.open(NewReplyComponent, dialogConfig);

        }
        if (courseName) {
            this.dialog.open(NewPostComponent, dialogConfig);
        }

    }
}
