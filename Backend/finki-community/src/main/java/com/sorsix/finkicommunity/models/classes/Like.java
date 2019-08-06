package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "post_id")
    private long postId;
    @Column(name = "user_id")
    private long userId;

    public Like(){}

    public Like(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }
}
