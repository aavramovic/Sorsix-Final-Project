import {Component, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Interfaces/Thread';
import {ThreadService} from '../../../services/thread.service';

@Component({
    selector: 'app-thread-bar',
    templateUrl: './thread-bar.component.html',
    styleUrls: ['./thread-bar.component.css']
})
export class ThreadBarComponent implements OnInit {
    threads: Thread[];
    constructor(private threadService: ThreadService) {
    }

    ngOnInit() {
        this.threadService.getTop10Posts().subscribe(threads => this.threads = threads);
    }

}
