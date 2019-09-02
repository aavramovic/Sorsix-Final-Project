import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of, Subject} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {API_URL, LOGIN_USER, USERS} from '../Models/global-const-url-paths';
import * as moment from 'moment';
import {ILoginResponse} from '../Models/Interfaces/ILoginResponse';
import {LoginResponse} from '../Models/Classes/LoginResponse';
import {Authorization} from '../Models/Enumeration/Authorization';
import {Router} from '@angular/router';
import {LoginRequest} from '../Models/Classes/login-request';
import {IRegisterUserResponse} from '../Models/Interfaces/IRegisterUserResponse';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
    public isLoggedIn$: Subject<boolean> = new Subject();

    constructor(private http: HttpClient,
                private router: Router) {
    }

    public getToken() {
        const token = localStorage.getItem('id_token');
        if (token && token.length) {
            return token;
        }
        return null;
    }

    login(username, password) {

        return this.http.post<ILoginResponse>(
            API_URL + 'login',
            new LoginRequest(username, password),
            {
                headers: new HttpHeaders(
                    {
                        'Content-Type': 'application/json'
                    }),
                observe: 'response'
            })
            .pipe(
                map(response => {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    if (response.status == 200) {
                        // @ts-ignore
                        const expiresAt = moment().add(response.body.expiresIn, 'second');
                        // @ts-ignore
                        localStorage.setItem('id_token', response.body.idToken);
                        localStorage.setItem('expires_at', (expiresAt.valueOf() - (new Date()).getMilliseconds()).toString());
                        // @ts-ignore
                        localStorage.setItem('role', response.body.role.toString());
                        // @ts-ignore
                        localStorage.setItem('username', response.body.username);
                        this.isLoggedIn$.next(true);
                        return response.body;
                    }
                    return new LoginResponse('0', '', Authorization.VISITOR, false, 'Incorrect username or password');
                }));
    }

    logout() {
        if (!AuthenticationService.isLoggedIn()) {
            return;
        }
        // remove user from local storage and set current user to null
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
        localStorage.removeItem('role');
        localStorage.removeItem('username');
        this.isLoggedIn$.next(false);
        this.router.navigate(['/']).then(() => {
        });
    }

    public static isLoggedIn(): boolean {
        const token = localStorage.getItem('id_token');
        return token && token.length !== 0;
    }

    static getExpiration() {
        const expiration = localStorage.getItem('expires_at');
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}
