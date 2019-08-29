import {Thread} from '../Classes/Thread';
import {IUser} from './IUser';
import {Authorization} from '../Enumeration/Authorization';

export interface IThread {
    id: number;
    username: string;
    courseName: string;
    timeOfPost: Date;
    noOfLikes: number;
    noOfComments: number;
    content: string;
    imageUrl: string;
    sex: string;
    role: string;
    title?: string;
    replies?: IThread[];
    usersLiked?: IUser[];
}
