export class PostReply {
    content: string;
    title: string;
    username: string;
    replyToPostId?: number;


    constructor(content: string, title: string, username: string, replyToPostId: number) {
        this.content = content;
        this.title = title;
        this.username = username;
        this.replyToPostId = replyToPostId;
    }
}
