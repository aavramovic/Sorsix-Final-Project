import {Injectable} from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable, throwError} from 'rxjs';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {API_URL} from '../Models/global-const-url-paths';
import {FormGroup} from '@angular/forms';
import {IRegisterUserResponse} from '../Models/Interfaces/IRegisterUserResponse';
import {PostUser} from '../Models/Classes/PostUser';
import {catchError, flatMap, tap} from 'rxjs/operators';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {AuthenticationService} from './authentication.service';
import {LoginResponse} from '../Models/Classes/LoginResponse';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient,
                private authService: AuthenticationService) {
    }

    public findUserByUserId(userId: number): Observable<User> {
        return this.http.get<User>(API_URL + '/user/' + userId);
    }

    public postNewUser(newUser: PostUser): Observable<LoginResponse> {
        // @ts-ignore
        return this.http.post<IRegisterUserResponse>('http://localhost:8080/forum/users/register', newUser)
            .pipe(
                flatMap(() => this.authService.login(newUser.username, newUser.password)),
                catchError(this.handleError)
            );
    }

    // noinspection JSMethodCanBeStatic
    private handleError(error: HttpErrorResponse): Observable<never> | null {
        console.log(error.error);
        return throwError(error);
    }


    loginUser(newUser: FormGroup) {

    }
}
