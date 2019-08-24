import {Injectable} from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable, of} from 'rxjs';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {API_URL, REGISTER_USER, USERS} from '../Models/global-const-url-paths';
import {FormGroup} from '@angular/forms';
import {IUser} from '../Models/Interfaces/IUser';
import {IRegisterUserResponse} from '../Models/Interfaces/IRegisterUserResponse';
import {PostUser} from '../Models/Classes/PostUser';
import {catchError} from 'rxjs/operators';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    public findUserByUserId(userId: number): Observable<User> {
        return this.http.get<User>(API_URL + '/user/' + userId);
    }

    public postNewUser(newUser: PostUser): Observable<IRegisterUserResponse> {
        console.log(newUser);
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer'
            })
        };
        const body = {
            username: newUser.username
        };
        return this.http.post<IRegisterUserResponse>('http://localhost:8080/forum/users/register', newUser, httpOptions)
            .pipe(
                catchError(this.handleError)
            );
    }

    private handleError(error: HttpErrorResponse): Observable<never> | null {
        console.log(error.error);
        return null;
    }


    loginUser(newUser: FormGroup) {

    }
}
