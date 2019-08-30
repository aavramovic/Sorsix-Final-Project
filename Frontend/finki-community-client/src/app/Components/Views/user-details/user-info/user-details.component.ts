import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {IUserDetailsResponse} from '../models/interfaces/iuser-details-response';
import {UserDetailsFollow} from '../models/classes/user-details-follow';
import {NewFollowing} from '../models/classes/new-following';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})


export class UserDetailsComponent implements OnInit {
  public user: IUserDetailsResponse;
  private URL = 'http://localhost:8080/forum/users/details?username=';

  constructor(
    private httpClient: HttpClient,
    private route: ActivatedRoute
    ) { }

  ngOnInit() {
    const username = this.route.snapshot.paramMap.get('username');
    const url = this.URL + username + '&loggedInUsername=' + localStorage.getItem('username');
    this.httpClient.get<IUserDetailsResponse>(url)
      .subscribe(
      u => {
        this.user = u;
        console.log(this.user);
      }
    );
  }

  followClicked(user: IUserDetailsResponse){
    this.httpClient.post(
      'http://localhost:8080/forum/users/follow',
      new NewFollowing(1, user.userId),
      { headers: new HttpHeaders(
          {
            'Content-Type': 'application/json'
          })
      }).subscribe(
      value => {
        if (!this.user.isFollowing) {
          this.user.userDetailsFollowers.push((new UserDetailsFollow(user.userId, 'fisnik', 'Fisnik', 'Limani')));
          this.user.numberOfFollowers++;
        } else {
          const index = this.user.userDetailsFollowers.findIndex(u => u.username === 'fisnik');
          this.user.userDetailsFollowers.splice(index, 1);
          this.user.numberOfFollowers--;
        }
        this.user.isFollowing = !this.user.isFollowing;
      }
    );
  }

    isLoggedIn(): boolean {
      return true;
    }

    isNotHisOwnProfile() {
      return this.user.username !== localStorage.getItem('username');
    }
}
