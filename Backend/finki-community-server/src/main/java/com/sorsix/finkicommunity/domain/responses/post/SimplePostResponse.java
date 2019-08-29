package com.sorsix.finkicommunity.domain.responses.post;

import com.sorsix.finkicommunity.domain.enums.Role;

public class SimplePostResponse implements Comparable<SimplePostResponse>{

    private long id;
    private long timeOfPost;
    private int noOfLikes;
    private int noOfComments;
    // User
    // This could be encapsulated to a SimplePostResponseUser class
    private String username;
    private char sex;
    private Role role;

    private String courseName;
    private String title;
    private String content;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCourseName() {
        return courseName;
    }

    public long getTimeOfPost() {
        return timeOfPost;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getNoOfLikes() {
        return noOfLikes;
    }

    public int getNoOfComments() {
        return noOfComments;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTimeOfPost(long timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNoOfLikes(int noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public void setNoOfComments(int noOfComments) {
        this.noOfComments = noOfComments;
    }

    @Override
    public int compareTo(SimplePostResponse o) {
        if(timeOfPost > o.timeOfPost)
            return -1;
        return 1;
    }
}
