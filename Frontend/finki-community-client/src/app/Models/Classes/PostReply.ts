export class PostReply {
    content: string;
    title: string;
    courseName: string;
    username: string;

    constructor(content: string, title: string, courseName: string, username: string) {
        this.content = content;
        this.title = title;
        this.courseName = courseName;
        this.username = username;
    }
}
