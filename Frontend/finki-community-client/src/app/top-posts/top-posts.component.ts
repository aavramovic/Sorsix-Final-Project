import { PostService } from './../services/post.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-top-posts',
  templateUrl: './top-posts.component.html',
  styleUrls: ['./top-posts.component.css']
})
export class TopPostsComponent implements OnInit {

  topPosts: Array<any> = [];

  constructor(private postService: PostService) { }

  ngOnInit() {
    this.postService.getTop10Posts()
        .subscribe(
          topPosts => this.topPosts = topPosts
        );
  }

}
