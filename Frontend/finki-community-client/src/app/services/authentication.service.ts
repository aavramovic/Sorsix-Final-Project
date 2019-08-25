import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {API_URL, LOGIN_USER, USERS} from '../Models/global-const-url-paths';
import {User} from '../Models/Classes/User';
import * as moment from 'moment';
import {ILoginResponse} from '../Models/Interfaces/ILoginResponse';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<any>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('id_token')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue() {
        return this.currentUserSubject.value;
    }

    login(username, password) {
        return this.http.post<ILoginResponse>(API_URL + USERS + LOGIN_USER, {username, password})
            .pipe(map(response => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                const expiresAt = moment().add(response.expiresIn, 'second');

                localStorage.setItem('id_token', response.idToken);
                localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
            }));
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');

        this.currentUserSubject.next(null);
    }

    public isLoggedIn() {
        return moment().isBefore(this.getExpiration());
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getExpiration() {
        const expiration = localStorage.getItem('expires_at');
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}
