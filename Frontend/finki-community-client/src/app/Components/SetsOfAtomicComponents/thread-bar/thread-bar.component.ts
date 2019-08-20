import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {Course} from '../../../Models/Classes/Course';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {Router} from '@angular/router';
import {Location} from '@angular/common';
import {UrlService} from '../../../services/url.service';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    @Input()
    selectedCourse?: string;
    numberOfPostsByPage: number = 10;//TODO napravi komponenta ili delche za biranje na ova
    thread$ = new Subject<Thread[]>();

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService,
                private router: Router,
                private url: UrlService) {
        this.selectedCourse = url.getLastPartOfUrl();
        if (this.selectedCourse == 'start') {
            this.selectedCourse = '';
        }
    }

    ngOnChanges() {
        console.log('Change detected');
        this.thread$.next();
    }

    ngOnInit() {
        this.thread$.pipe(switchMap(() =>
            this.threadService.getTop10Posts()))
            .subscribe(threads => this.threads = threads);
        this.thread$.next();
    }

}
