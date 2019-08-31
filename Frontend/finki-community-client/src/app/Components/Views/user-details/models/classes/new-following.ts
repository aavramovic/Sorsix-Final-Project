export class NewFollowing {
  usernameFollowing: string;
  userIdFollowed: number;

  constructor(userIdFollowing: string, userIdFollowed: number){
    this.usernameFollowing = userIdFollowing;
    this.userIdFollowed = userIdFollowed;
  }
}
