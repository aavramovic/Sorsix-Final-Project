import {Component, Input, OnInit} from '@angular/core';
import {UserDetailsFollow} from '../models/classes/user-details-follow';

@Component({
  selector: 'app-user-followings',
  templateUrl: './user-followings.component.html',
  styleUrls: ['./user-followings.component.css']
})
export class UserFollowingsComponent implements OnInit {

  @Input() numberOfFollowings: number;
  @Input() userDetailsFollowings: Array<UserDetailsFollow>;

  constructor() { }

  ngOnInit() {
  }

}
