export class Thread {
    noOfLikes: number;
    title: string;
    timeOfPost: Date;
    content: string;

    username: string;
    courseName: string;

    postId: string;
    userId: string;
    courseId: string;
    comments: Thread[];


    constructor(postId: string, userId: string, courseId: string, timeOfPost: Date, content: string, title?: string) {
        this.title = title;
        this.timeOfPost = timeOfPost;
        this.content = content;
        this.postId = postId;
        this.userId = userId;
        this.courseId = courseId;
        this.noOfLikes = 10;
        this.comments = [];
    }
}
