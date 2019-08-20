import {Injectable} from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {API_URL} from '../Models/Classes/GlobalPathStaticVariables';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    public getUserByUserId(userId: number): Observable<User> {
        return this.http.get<User>(API_URL + '/user/' + userId);
    }


}
