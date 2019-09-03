import {Component, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {NavigationEnd, Router} from '@angular/router';
import {UrlService} from '../../../services/url.service';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {NewPostComponent} from '../../AtomicComponents/new-post/new-post.component';
import {AuthenticationService} from '../../../services/authentication.service';
import {switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    selectedCourse = '';
    numberOfPostsByPage = '10';
    threadByCourse$ = new Subject();

    isLoggedIn: boolean;
    p: number;

    constructor(private threadService: ThreadService,
                private router: Router,
                private url: UrlService,
                public dialog: MatDialog,
                private authService: AuthenticationService) {
    }

    onValueChange() {
        this.threadByCourse$.next();
    }

    urlChange() {
        this.selectedCourse = this.url.getLastPartOfUrl();

        if (this.selectedCourse === 'start' || this.selectedCourse === 'threads') {
            this.selectedCourse = '';
        }

        this.threadByCourse$.next();
    }

    ngOnInit() {
        this.router.events.subscribe(e => {
            if (e instanceof NavigationEnd && this.url.hasStartInUrl()) {
                this.urlChange();
            }
        });
        this.selectedCourse = this.url.getLastPartOfUrl();

        // TODO: ne go menjaj te mrzi
        if (this.selectedCourse === 'start' || this.selectedCourse === 'threads') {
            this.selectedCourse = '';
        }

        this.threadByCourse$.pipe(switchMap(() =>
            this.threadService.getTopNThreadsByCourse(+this.numberOfPostsByPage, this.selectedCourse)))
            .subscribe(threads =>
                this.threads = threads.filter(thread => {
                    return !thread.repliedTo;
                }));

        this.threadByCourse$.next();

        this.isLoggedIn = AuthenticationService.isLoggedIn();

        this.authService.isLoggedIn$.subscribe(r => {
            this.isLoggedIn = r;
        });

        this.threadService.invokeEvent.subscribe(() => {
            this.threadByCourse$.next();
        });
    }

    openDialog(threadId?: string): void {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.disableClose = true;
        dialogConfig.height = 'max-content';

        dialogConfig.width = '600px';
        dialogConfig.data = {
            postId: threadId
        };

        this.dialog.open(NewPostComponent, dialogConfig);
        this.threadByCourse$.next();
    }

    pageChanged($event: number) {
        this.p = $event;
        window.scroll(0, 0);
    }
}

