import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
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

    onValueChange() {
        this.threadByCourse$.next();
    }

    urlChange() {
        this.selectedCourse = this.url.getLastPartOfUrl();

        if (this.selectedCourse == 'start' || this.selectedCourse == 'threads') {
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

        //TODO: ne go menjaj te mrzi
        if (this.selectedCourse == 'start' || this.selectedCourse == 'threads') {
            this.selectedCourse = '';
        }

        this.threadByCourse$.pipe(switchMap(() =>
            this.threadService.getTopNThreadsByCourse(this.numberOfPostsByPage, this.selectedCourse)))
            .subscribe(threads => this.threads = threads);

        this.threadByCourse$.next();
    }


}

