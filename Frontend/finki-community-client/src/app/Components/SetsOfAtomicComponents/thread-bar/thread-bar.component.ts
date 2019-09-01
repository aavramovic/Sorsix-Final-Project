import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {NavigationEnd, Router} from '@angular/router';
import {UrlService} from '../../../services/url.service';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {NewPostComponent} from '../../AtomicComponents/new-post/new-post.component';
import {MatRadioChange} from '@angular/material/radio';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    @Input() checked = true;
    threads: Thread[];
    selectedCourse = '';
    numberOfPostsByPage = '10'; // TODO napravi komponenta ili delche za biranje na ova
    threadByCourse$ = new Subject();

    isLoggedIn: boolean;

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService,
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
            .subscribe(threads => this.threads = threads);

        this.threadByCourse$.next();

        this.isLoggedIn = AuthenticationService.isLoggedIn();

        this.authService.isLoggedIn$.subscribe(r => {
            this.isLoggedIn = r;
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
        // We don't return data back from the modal components instead they communicate themselves
        // Maybe let it return a boolean that tells us
        this.dialog.open(NewPostComponent, dialogConfig);

    }
}

