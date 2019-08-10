import {Authorization} from '../Enumeration/Authorization';
import {Course} from './Course';

export class User {
    private userId: string;
    username: string;
    private firstName: string;
    private lastName: string;
    private password: string;
    private _authorization: Authorization;
    private email: string;
    private pictureUrl: string;
    private numberOfPosts: number;
    private _imageUrl: string;
    //ManyToMany
    private courses: Set<Course>;


    constructor(firstName: string, lastName: string, username: string, password: string, email: string, userId: string) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = userId;
        this._authorization = Authorization.VISITOR;
        this.pictureUrl = '#';
        this.numberOfPosts = 0;
        this.courses = new Set<Course>();
    }


    set authorization(value: Authorization) {
        this._authorization = value;
    }

    get imageUrl(): string {
        return this._imageUrl;
    }
}
