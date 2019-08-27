package com.sorsix.finkicommunity.domain.responses.post;

public class MockPost implements Comparable<MockPost>{
    public long id;
    public long timeOfPost;
    public int noOfLikes;
    public int noOfComments;
    public String title;
    public String content;

    public String username;

    public String courseName;

    public boolean isLiked;

    @Override
    public int compareTo(MockPost o) {
        return 0;
    }
}
