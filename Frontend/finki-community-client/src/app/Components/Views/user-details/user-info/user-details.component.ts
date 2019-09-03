import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {IUserDetailsResponse} from '../models/interfaces/iuser-details-response';
import {UserDetailsFollow} from '../models/classes/user-details-follow';
import {NewFollowing} from '../models/classes/new-following';
import {MatSnackBar} from '@angular/material';
import {IFollowResponse} from '../models/interfaces/ifollow-response';
import {Subject} from 'rxjs';
import {flatMap} from 'rxjs/operators';

@Component({
    selector: 'app-user-details',
    templateUrl: './user-details.component.html',
    styleUrls: ['./user-details.component.css']
})


export class UserDetailsComponent implements OnInit {
    public user: IUserDetailsResponse;
    private URL = 'http://localhost:8080/forum/users/details?username=';
    private userDetails$ = new Subject();

    constructor(
        private httpClient: HttpClient,
        private route: ActivatedRoute,
        private snackBar: MatSnackBar,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.router.events.subscribe(
            e => {
                if (e instanceof NavigationEnd) {
                    this.urlChange();
                }
            }
        );

        this.userDetails$.pipe(
            flatMap(
                () => {
                    const username = this.route.snapshot.paramMap.get('username');

                    const userDetailsUrl = this.URL + username +
                        (localStorage.getItem('username') ? '&loggedInUsername=' + localStorage.getItem('username') : '');
                    return this.httpClient.get<IUserDetailsResponse>(userDetailsUrl);
                }
            )
        ).subscribe(
            u => {
                this.user = u;
            }, () => {
                this.openSnackBar('No such username');
                this.router.navigate(['/']).then(() => {
                });
            }
        );
        this.userDetails$.next();
    }

    urlChange() {
        this.userDetails$.next();
    }

    followClicked(user: IUserDetailsResponse) {
        this.httpClient.post<IFollowResponse>(
            'http://localhost:8080/forum/users/follow',
            new NewFollowing(localStorage.getItem('username'), user.userId),
            {
                headers: new HttpHeaders(
                    {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + localStorage.getItem('id_token')
                    })
            }).subscribe(
            value => {
                if (!this.user.isFollowing) {
                    this.user.userDetailsFollowers.push((new UserDetailsFollow(value.id, value.username, value.firstName, value.lastName)));
                    this.user.numberOfFollowers++;
                } else {
                    const index = this.user.userDetailsFollowers.findIndex(u => u.username === value.username);
                    this.user.userDetailsFollowers.splice(index, 1);
                    this.user.numberOfFollowers--;
                }
                this.user.isFollowing = !this.user.isFollowing;
            }
        );
    }

    openSnackBar(message: string) {
        this.snackBar.open(message, 'Close', {
            duration: 3000,
        });
    }

    isLoggedIn(): boolean {
        return localStorage.getItem('username') !== null;
    }

    isNotHisOwnProfile() {
        return this.user.username !== localStorage.getItem('username');
    }
}
