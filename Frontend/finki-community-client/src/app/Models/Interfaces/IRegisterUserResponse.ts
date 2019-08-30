import {FormControl, Validators} from '@angular/forms';
import {IThread} from './IThread';

export interface IRegisterUserResponse {
    userId: number;
    username: string;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    birthdate: number;
    numberOfPosts: number;
    role: string;
    active: boolean;
    // //
    // pictureUrl: string,
    // permissions: string,
    // active: boolean,
    // numberOfFollowers: number,
    // numberOfFollowings: number,
    // follows,
    // followedBy,
    // // posts: IThread[],
    // postsLiked
}
