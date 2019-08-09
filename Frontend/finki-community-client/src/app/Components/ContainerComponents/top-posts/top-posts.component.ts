import {Component, OnInit} from '@angular/core';
import {ThreadService} from '../../../services/thread.service';

@Component({
    selector: 'app-top-posts',
    templateUrl: './top-posts.component.html',
    styleUrls: ['./top-posts.component.css']
})
export class TopPostsComponent implements OnInit {

    topPosts: Array<any> = [];

    constructor(private threadService: ThreadService) {
    }

    ngOnInit() {/*
    this.threadService.getTop10Posts()
        .subscribe(
          topPosts => this.topPosts = topPosts
        );*/
    }

}
