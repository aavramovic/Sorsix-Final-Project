import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Subject} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {API_URL} from '../Models/global-const-url-paths';
import * as moment from 'moment';
import {ILoginResponse} from '../Models/Interfaces/ILoginResponse';
import {LoginResponse} from '../Models/Classes/LoginResponse';
import {Authorization} from '../Models/Enumeration/Authorization';
import {Router} from '@angular/router';
import {LoginRequest} from '../Models/Classes/login-request';

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
                    if (response.status == 200) {
                        const expiresAt = moment().add(response.body.expiresIn, 'second');
                        localStorage.setItem('id_token', response.body.idToken);
                        localStorage.setItem('expires_at', (expiresAt.valueOf() - (new Date()).getMilliseconds()).toString());
                        localStorage.setItem('role', response.body.role.toString());
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
