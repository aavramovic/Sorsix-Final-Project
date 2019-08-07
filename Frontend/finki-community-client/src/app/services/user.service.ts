import { Injectable } from '@angular/core';
import {User} from '../Models/Classes/User';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getUserByUserId(userId: number): Observable<User>{
    //TODO: Smeni go ova kako sho treba da bide
    return this.http.get<User>("nesho/user/id");
  }
}
