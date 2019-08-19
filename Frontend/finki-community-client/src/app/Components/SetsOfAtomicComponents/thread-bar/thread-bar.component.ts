import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {Course} from '../../../Models/Classes/Course';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    @Input()
    selectedCourse?: Course;
    numberOfPostsByPage: number = 10;//TODO napravi komponenta ili delche za biranje na ova
    thread$ = new Subject<Thread[]>();

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService) {
    }

    ngOnChanges() {
        // console.log('Change detected');
        this.thread$.next();
    }

    ngOnInit() {
        this.thread$.pipe(switchMap(() =>
            this.threadService.getTopNThreadsByCourse(this.numberOfPostsByPage, this.selectedCourse.courseId)))
            .subscribe(threads => this.threads = threads);
        this.thread$.next();

        //TODO:// trgni go delayot i smeni da ne e mock
        //TODO:// oninit da se filtrira spored parametarot vo linkot za koi threads da gi prikazhuva
        this.threadService.getTopNPosts(this.numberOfPostsByPage).subscribe(threads => this.threads = threads);

        // console.log('Init');
    }

}
