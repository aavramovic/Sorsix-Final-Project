package com.sorsix.finkicommunity.domain.response.post;

public class SimplePostResponse {

    private long id;
    private long timeOfPost;
    private int noOfLikes;
    private int noOfComments;
    private String username;
    private String courseName;
    private String title;
    private String content;


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
}
