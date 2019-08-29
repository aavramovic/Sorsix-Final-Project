export class NewPostLikeRequest {
    username: string;
    postId: number;

    constructor(username: string, postId: number) {
        this.username = username;
        this.postId = postId;
    }
}
