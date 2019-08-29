import {Component, Input, OnInit} from '@angular/core';
import {UserDetailsPost} from '../models/classes/user-details-post';

@Component({
  selector: 'app-user-posts-liked',
  templateUrl: './user-posts-liked.component.html',
  styleUrls: ['./user-posts-liked.component.css']
})
export class UserPostsLikedComponent implements OnInit {

  @Input() numberOfPostsLiked: number;
  @Input() userDetailsPostsLiked: Array<UserDetailsPost>;

  constructor() { }

  ngOnInit() {
  }

}
