export interface IThread {
    postId: number;
    userId: number;
    courseId: number;
    title: string;
    content: string;
    timestamp: number;
    numberOfLikes: number;
    numberOfReplies: number;
}
