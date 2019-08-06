package com.sorsix.finkicommunity.domain.entities;


import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(name="user_id")
    private long userId;
    @Column(name="number_of_likes")
    private int numberOfLikes;
    @Column(name="number_of_replies")
    private int numberOfReplies;

    public Post(){}

    public Post(long userId, int numberOfLikes, int numberOfReplies) {
        this.userId = userId;
        this.numberOfLikes = numberOfLikes;
        this.numberOfReplies = numberOfReplies;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }
}
