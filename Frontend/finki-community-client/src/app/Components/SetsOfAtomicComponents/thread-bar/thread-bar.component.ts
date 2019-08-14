import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {Course} from '../../../Models/Classes/Course';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    @Input()
    selectedCourse?: Course;
    filteredThreads: Thread[];

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService) {
    }

    ngOnChanges() {
        // console.log('Change detected');
        this.filteredThreads = this.selectedCourse ? this.threads.filter(thread => thread.courseId == this.selectedCourse._courseId) : this.threads;
    }

    ngOnInit() {
        //TODO:// trgni go delayot i smeni da ne e mock
        //TODO:// oninit da se filtrira spored parametarot vo linkot za koi threads da gi prikazhuva
        this.mock.delay().then(() =>
            this.threadService.getMockThreads()
                .subscribe(threads => this.threads = threads));
        // this.threadService.getMockThreads().subscribe(threads => this.threads = threads);

        // console.log('Init');
    }

}
