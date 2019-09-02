import {Component, Input, OnInit} from '@angular/core';
import {UserDetailsFollow} from '../models/classes/user-details-follow';

@Component({
  selector: 'app-user-followers',
  templateUrl: './user-followers.component.html',
  styleUrls: ['./user-followers.component.css']
})
export class UserFollowersComponent implements OnInit {

  @Input() numberOfFollowers: number;
  @Input() userDetailsFollowers: Array<UserDetailsFollow>;

  constructor() { }

  ngOnInit() {
  }

}
