export class PostThread {
    content: string;
    title: string;
    replyToPostId?: number;
    courseName?: string;
    username: string;

    constructor(content: string, title: string, username: string, courseName?: string, replyToPostId?: number) {
        this.content = content;
        this.title = title;
        this.replyToPostId = replyToPostId;
        this.courseName = courseName;
        this.username = username;
    }
}
