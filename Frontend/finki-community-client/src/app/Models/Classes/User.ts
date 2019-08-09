import {Authorization} from '../Enumeration/Authorization';
import {Course} from './Course';

export class User {
  private _username: string;
  private firstName: string;
  private lastName: string;
  private password: string;
  private authorization: Authorization;
  private email: string;
  private pictureUrl: string;
  private numberOfPosts: string;
  private _imageUrl: string;
  //ManyToMany
  private courses: Course[];


  get username(): string {
    return this._username;
  }


  get imageUrl(): string {
    return this._imageUrl;
  }
}
