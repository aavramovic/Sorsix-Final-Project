import {Injectable} from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {GlobalPathStaticVariables} from '../Models/Classes/GlobalPathStaticVariables';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    readonly _API_URL: string = GlobalPathStaticVariables.API_URL;

    constructor(private http: HttpClient) {
    }

    public getUserByUserId(userId: number): Observable<User> {
        return this.http.get<User>(this._API_URL + '/user/' + userId);
    }
}
