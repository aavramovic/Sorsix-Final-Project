import {Component, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];

    constructor(private threadService: ThreadService,
                private mock: MockClassesCreationService) {
    }

    ngOnInit() {
        //TODO:// trgni go delayot
        this.mock.delay(2000).then(() =>
            this.threadService.getMockThreads().subscribe(threads => this.threads = threads));
        // this.threadService.getMockThreads().subscribe(threads => this.threads = threads);
    }

}
