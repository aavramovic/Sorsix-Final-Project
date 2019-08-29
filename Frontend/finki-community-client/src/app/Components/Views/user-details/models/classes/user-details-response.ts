import {UserDetailsFollow} from './user-details-follow';
import {UserDetailsPost} from './user-details-post';

export class UserDetailsResponse {
  constructor(
        public userId: number,
        public username: string,
        public email: string,
        public firstName: string,
        public lastName: string,
        public sex: string,
        public role: string,
        public isFollowing: boolean,

        public numberOfPosts: number,
        public numberOfFollowers: number,
        public numberOfFollowings: number,
        public numberOfPostsLiked: number,
        public userDetailsPosts: Array<UserDetailsPost>,
        public userDetailsFollowers: Array<UserDetailsFollow>,
        public userDetailsFollowings: Array<UserDetailsFollow>,
        public userDetailsPostsLiked: Array<UserDetailsPost>
  ) {
  }
}
