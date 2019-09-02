import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of, Subject} from 'rxjs';
import {map} from 'rxjs/operators';
import {API_URL, LOGIN_USER, USERS} from '../Models/global-const-url-paths';
import * as moment from 'moment';
import {ILoginResponse} from '../Models/Interfaces/ILoginResponse';
import {LoginResponse} from '../Models/Classes/LoginResponse';
import {Authorization} from '../Models/Enumeration/Authorization';
import {Router} from '@angular/router';

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

    login(username, password): Observable<ILoginResponse> {

        return this.http.post<ILoginResponse>(API_URL + USERS + LOGIN_USER, {username, password})
            .pipe(
                map(response => {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    if (response.valid) {
                        const expiresAt = moment().add(response.expiresIn, 'second');
                        localStorage.setItem('id_token', response.idToken);
                        localStorage.setItem('expires_at', (expiresAt.valueOf() - (new Date()).getMilliseconds()).toString());
                        localStorage.setItem('role', response.role.toString());
                        localStorage.setItem('username', response.username);
                        this.isLoggedIn$.next(true);
                        return response;
                    }
                    return new LoginResponse('0', '', Authorization.VISITOR, response.valid, response.errorMessage);
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
