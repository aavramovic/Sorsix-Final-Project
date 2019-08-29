import {Course} from './Course';
import {Authorization} from '../Enumeration/Authorization';

export class Thread {
    threadId: number;
    username: string;
    courseName: string;
    timeOfPost: Date;
    numberOfLikes: number;
    numberOfComments: number;
    content: string;
    imageUrl: string;
    title: string;
    sex: string;
    role: Authorization;

    comments: Thread[];

    constructor(threadId: number,
                username: string,
                courseName: string,
                timeOfPost: Date,
                noOfLikes: number,
                noOfComments: number,
                content: string,
                imageUrl: string,
                title: string,
                sex: string,
                role: Authorization) {
        this.threadId = threadId;
        this.username = username;
        this.courseName = courseName;
        this.timeOfPost = timeOfPost;
        this.numberOfLikes = noOfLikes;
        this.numberOfComments = noOfComments;
        this.content = content;
        this.imageUrl = imageUrl;
        this.title = title;
        this.sex = sex;
        this.role = role;

        this.comments = [];
    }
}
