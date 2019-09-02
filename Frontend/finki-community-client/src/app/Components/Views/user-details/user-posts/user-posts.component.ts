import {Component, Input, OnInit} from '@angular/core';
import {UserDetailsPost} from '../models/classes/user-details-post';

@Component({
  selector: 'app-user-posts',
  templateUrl: './user-posts.component.html',
  styleUrls: ['./user-posts.component.css']
})
export class UserPostsComponent implements OnInit {

  @Input() numberOfPosts: number;
  @Input() posts: Array<UserDetailsPost>;

  constructor() { }

  ngOnInit() {
  }

}
