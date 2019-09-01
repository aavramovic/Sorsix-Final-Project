import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {IUserDetailsResponse} from '../models/interfaces/iuser-details-response';
import {UserDetailsFollow} from '../models/classes/user-details-follow';
import {NewFollowing} from '../models/classes/new-following';
import {MatSnackBar} from '@angular/material';

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
        private route: ActivatedRoute,
        private _snackBar: MatSnackBar,
        private router: Router
    ) {
    }

    ngOnInit() {
        const username = this.route.snapshot.paramMap.get('username');
        const url = this.URL + username + (localStorage.getItem('username') ? '&loggedInUsername=' + localStorage.getItem('username') : '');
        this.httpClient.get<IUserDetailsResponse>(url)
            .subscribe(
                u => {
                    this.user = u;
                    console.log(this.user);
                }, error => {
                    this.openSnackBar('No such username');
                    this.router.navigate(['/']).then(r => {
                    });
                }
            );
    }

    followClicked(user: IUserDetailsResponse) {
        this.httpClient.post(
            'http://localhost:8080/forum/users/follow',
            new NewFollowing(localStorage.getItem('username'), user.userId),
            {
                headers: new HttpHeaders(
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

    openSnackBar(message: string) {
        this._snackBar.open(message, 'Close', {
            duration: 3000,
        });
    }

    isLoggedIn(): boolean {
        return localStorage.getItem('username') === null ? false : true;
    }

    isNotHisOwnProfile() {
        return this.user.username !== localStorage.getItem('username');
    }
}
