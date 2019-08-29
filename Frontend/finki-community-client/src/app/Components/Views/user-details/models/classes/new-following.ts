export class NewFollowing {
  userIdFollowing: number;
  userIdFollowed: number;

  constructor(userIdFollowing: number, userIdFollowed: number){
    this.userIdFollowing = userIdFollowing;
    this.userIdFollowed = userIdFollowed;
  }
}
