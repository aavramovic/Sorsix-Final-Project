import {Authorization} from '../Enumeration/Authorization';

export class Thread {
    threadId: number;

    courseName: string;
    courseCode: string;

    timeOfPost: Date;
    numberOfLikes: number;
    numberOfComments: number;
    content: string;
    title: string;

    username: string;
    sex: string;
    role: Authorization;
    isLiked: boolean;

    imageUrl: string;
    comments: Thread[];

    repliedTo: number;


    constructor(threadId: number,
                courseName: string,
                courseCode: string,
                timeOfPost: Date,
                numberOfLikes: number,
                numberOfComments: number,
                content: string,
                title: string,
                username: string,
                sex: string,
                role: Authorization,
                isLiked: boolean,
                imageUrl: string,
                repliedTo: number
    ) {
        this.threadId = threadId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.timeOfPost = timeOfPost;
        this.numberOfLikes = numberOfLikes;
        this.numberOfComments = numberOfComments;
        this.content = content;
        this.title = title;
        this.username = username;
        this.sex = sex;
        this.role = role;
        this.isLiked = isLiked;
        this.imageUrl = imageUrl;
        this.comments = [];
        this.repliedTo = repliedTo;
    }
}
