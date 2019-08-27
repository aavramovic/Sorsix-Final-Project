package com.sorsix.finkicommunity.domain.responses.user_details;

import com.fasterxml.jackson.annotation.JsonGetter;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public int getNumberOfPostsLiked() {
        return numberOfPostsLiked;
    }

    public void setNumberOfPostsLiked(int numberOfPostsLiked) {
        this.numberOfPostsLiked = numberOfPostsLiked;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public int getNumberOfFollowings() {
        return numberOfFollowings;
    }

    public void setNumberOfFollowings(int numberOfFollowings) {
        this.numberOfFollowings = numberOfFollowings;
    }

    public List<UserDetailsPost> getUserDetailsPosts() {
        return userDetailsPosts;
    }

    public void setUserDetailsPost(List<UserDetailsPost> userDetailsPosts) {
        this.userDetailsPosts = userDetailsPosts;
    }

    public List<UserDetailsFollow> getUserDetailsFollowers() {
        return userDetailsFollowers;
    }

    public void setUserDetailsFollowers(List<UserDetailsFollow> userDetailsFollowers) {
        this.userDetailsFollowers = userDetailsFollowers;
    }

    public List<UserDetailsFollow> getUserDetailsFollowings() {
        return userDetailsFollowings;
    }

    public void setUserDetailsFollowings(List<UserDetailsFollow> userDetailsFollows) {
        this.userDetailsFollowings = userDetailsFollows;
    }

    public void setUserDetailsPosts(List<UserDetailsPost> userDetailsPosts) {
        this.userDetailsPosts = userDetailsPosts;
    }

    public List<UserDetailsPost> getUserDetailsPostsLiked() {
        return userDetailsPostsLiked;
    }

    public void setUserDetailsPostsLiked(List<UserDetailsPost> userDetailsPostsLiked) {
        this.userDetailsPostsLiked = userDetailsPostsLiked;
    }

    @JsonGetter
    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }
}
