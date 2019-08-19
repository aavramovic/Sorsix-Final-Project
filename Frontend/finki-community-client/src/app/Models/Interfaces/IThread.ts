import {Thread} from '../Classes/Thread';
import {IUser} from './IUser';

export interface IThread {
    postId: number,
    username: string,
    courseName: string,
    timestamp: Date,
    numberOfLikes: number,
    numberOfComments: number,
    content: string,
    imageUrl: string,
    title?: string,
    replies?: IThread[],
    usersLiked?:IUser[];
}
