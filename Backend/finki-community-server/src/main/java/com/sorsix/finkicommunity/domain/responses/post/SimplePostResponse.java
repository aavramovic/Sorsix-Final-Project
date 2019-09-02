package com.sorsix.finkicommunity.domain.responses.post;

import com.sorsix.finkicommunity.domain.enums.Role;

public class SimplePostResponse implements Comparable<SimplePostResponse>{
    public long id;
    public long timeOfPost;
    public int noOfLikes;
    public int noOfComments;

    // User/Author data
    public String username;
    public char sex;
    public Role role;
    public boolean isLiked;

    public String courseName;
    public String courseCode;

    public String title;
    public String content;

    public Long repliedTo;

    @Override
    public int compareTo(SimplePostResponse o) {
        if(timeOfPost > o.timeOfPost)
            return -1;
        return 1;
    }
}
