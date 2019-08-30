import {Thread} from '../Classes/Thread';
import {IUser} from './IUser';
import {Authorization} from '../Enumeration/Authorization';

export interface IThread {
    id: number;
    courseName: string;
    timeOfPost: Date;
    noOfLikes: number;
    noOfComments: number;
    content: string;
    imageUrl: string;

    username: string;
    sex: string;
    role: string;
    isLiked: boolean;

    title?: string;
    replies?: IThread[];
    usersLiked?: IUser[];
}
