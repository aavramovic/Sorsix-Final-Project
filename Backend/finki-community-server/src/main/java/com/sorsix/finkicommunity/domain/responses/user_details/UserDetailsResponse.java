package com.sorsix.finkicommunity.domain.responses.user_details;

import com.sorsix.finkicommunity.domain.enums.Role;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsResponse {
    public long userId;
    public String username;
    public String email;
    public String firstName;
    public String lastName;
    public Character sex;
    public long birthdate;
    public Role role;
    public boolean isFollowing;

    public int numberOfPosts;
    public int numberOfPostsLiked;
    public int numberOfFollowers;
    public int numberOfFollowings;

    public List<UserDetailsPost> userDetailsPosts = new ArrayList<>();
    public List<UserDetailsFollow> userDetailsFollowers = new ArrayList<>();
    public List<UserDetailsFollow> userDetailsFollowings = new ArrayList<>();
    public List<UserDetailsPost> userDetailsPostsLiked = new ArrayList<>();
}
