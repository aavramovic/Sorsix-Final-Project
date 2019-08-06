package com.sorsix.finkicommunity.models.classes;


import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="user_id")
    private long userId;
    @Column(name="number_of_likes")
    private int numberOfLikes;
    @Column(name="number_of_replies")
    private int numberOfReplies;



    public Post(long userId, int numberOfLikes, int numberOfReplies) {
        this.userId = userId;
        this.numberOfLikes = numberOfLikes;
        this.numberOfReplies = numberOfReplies;
    }

    public long getId() {
        return id;
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
