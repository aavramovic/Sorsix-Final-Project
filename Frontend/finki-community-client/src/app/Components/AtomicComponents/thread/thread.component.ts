import {Component, Input, OnInit} from '@angular/core';
import {Thread} from '../../../Models/Classes/Thread';
import {ThreadService} from '../../../services/thread.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';

@Component({
    selector: 'app-thread',
    templateUrl: './thread.component.html',
    styleUrls: ['./thread.component.css']
})
export class ThreadComponent implements OnInit {
    @Input()
    private thread: Thread;
    replie$ = new Subject<Thread[]>();
    replies: Thread[];

    isOpen: boolean = false;

    constructor(
        private threadService: ThreadService) {

    }

    ngOnInit() {
    }

    loadComments() {
        this.replie$.pipe(switchMap(() =>
            this.threadService.getReplies(this.thread.threadId)
        )).subscribe(replies => this.replies = replies);
    }
}
