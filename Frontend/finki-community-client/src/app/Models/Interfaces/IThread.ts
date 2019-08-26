import {Thread} from '../Classes/Thread';
import {IUser} from './IUser';

export interface IThread {
    id: number,
    username: string,
    courseName: string,
    timeOfPost: Date,
    noOfLikes: number,
    noOfComments: number,
    content: string,
    imageUrl: string,
    title?: string,
    replies?: IThread[],
    usersLiked?: IUser[];
}
