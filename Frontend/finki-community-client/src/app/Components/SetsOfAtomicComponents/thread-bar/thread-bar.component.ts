import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {Course} from '../../../Models/Classes/Course';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {NavigationEnd, NavigationStart, Router} from '@angular/router';
import {Location} from '@angular/common';
import {UrlService} from '../../../services/url.service';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    selectedCourse: string = '';
    numberOfPostsByPage: number = 10;//TODO napravi komponenta ili delche za biranje na ova
    // thread$ = new Subject();
    threadByCourse$ = new Subject();

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService,
                private router: Router,
                private url: UrlService) {
    }

    urlChange() {
        this.selectedCourse = this.url.getLastPartOfUrl();

        if (this.selectedCourse == 'start') {
            this.selectedCourse = '';
        }

        this.threadByCourse$.next();
    }

    ngOnInit() {
        // this.thread$.pipe(switchMap(() =>
        //     this.threadService.getTopNPosts(this.numberOfPostsByPage)))
        //     .subscribe(threads => this.threads = threads);
        // this.thread$.next();

        this.router.events.subscribe(e => {
            if (e instanceof NavigationEnd && this.url.containsStartInUrl()) {
                this.urlChange();
            }
        });
        this.selectedCourse = this.url.getLastPartOfUrl();
        if (this.selectedCourse == 'start') {
            this.selectedCourse = '';
        }

        this.threadByCourse$.pipe(switchMap(() =>
            this.threadService.getTopNThreadsByCourse(this.numberOfPostsByPage, this.selectedCourse)))
            .subscribe(threads => this.threads = threads);

        this.threadByCourse$.next();
    }
}
