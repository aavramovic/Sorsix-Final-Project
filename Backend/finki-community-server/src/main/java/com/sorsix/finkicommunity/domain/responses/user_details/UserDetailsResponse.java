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
    public Role role;

    public int numberOfPosts;
    public int numberOfFollowers;
    public int numberOfFollowings;

    public List<UserDetailsPost> userDetailsPosts = new ArrayList<>();
    public List<UserDetailsFollower> userDetailsFollowers = new ArrayList<>();
    public List<UserDetailsFollowing> userDetailsFollowings = new ArrayList<>();

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

    public List<UserDetailsFollower> getUserDetailsFollowers() {
        return userDetailsFollowers;
    }

    public void setUserDetailsFollowers(List<UserDetailsFollower> userDetailsFollowers) {
        this.userDetailsFollowers = userDetailsFollowers;
    }

    public List<UserDetailsFollowing> getUserDetailsFollowings() {
        return userDetailsFollowings;
    }

    public void setUserDetailsFollowings(List<UserDetailsFollowing> userDetailsFollowings) {
        this.userDetailsFollowings = userDetailsFollowings;
    }
}
