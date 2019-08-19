import {Course} from './Course';

export class Thread {
    threadId: number;
    username: string;
    courseName: string;
    timeOfPost: Date;
    noOfLikes: number;
    noOfComments: number;
    content: string;
    imageUrl: string;
    title: string;

    comments: Thread[];

    constructor(threadId: number,
                username: string,
                courseName: string,
                timeOfPost: Date,
                noOfLikes: number,
                noOfComments: number,
                content: string,
                imageUrl: string,
                title?: string) {
        this.threadId = threadId;
        this.username = username;
        this.courseName = courseName;
        this.timeOfPost = timeOfPost;
        this.noOfLikes = noOfLikes;
        this.noOfComments = noOfComments;
        this.content = content;
        this.imageUrl = 'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_1280.png';
        this.title = title;

        this.comments = [];
    }
}
