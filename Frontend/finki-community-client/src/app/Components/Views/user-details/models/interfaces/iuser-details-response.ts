import {IUserDetailsPost} from './iuser-details-post';
import {IUserDetailsFollow} from './iuser-details-follow';

export interface IUserDetailsResponse {
  userId: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  sex: string;
  role: string;
  birthdate: number;
  isFollowing: boolean;

  numberOfPosts: number;
  numberOfFollowers: number;
  numberOfFollowings: number;
  numberOfPostsLiked: number;

  userDetailsPosts: Array<IUserDetailsPost>;
  userDetailsFollowers: Array<IUserDetailsFollow>;
  userDetailsFollowings: Array<IUserDetailsFollow>;
  userDetailsPostsLiked: Array<IUserDetailsPost>;
}
