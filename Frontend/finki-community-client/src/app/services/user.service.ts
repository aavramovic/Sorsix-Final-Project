import {Injectable} from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {GlobalPathStaticVariables} from '../Models/Classes/GlobalPathStaticVariables';
import {MockClassesCreationService} from './mock-classes-creation.service';
import {Course} from '../Models/Classes/Course';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    readonly _API_URL: string = GlobalPathStaticVariables.API_URL;

    constructor(private http: HttpClient,
                private mock: MockClassesCreationService) {
    }

    public getUserByUserId(userId: number): Observable<User> {
        return this.http.get<User>(this._API_URL + '/user/' + userId);
    }


}
