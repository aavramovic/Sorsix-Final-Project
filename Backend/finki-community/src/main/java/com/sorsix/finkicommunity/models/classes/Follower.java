package com.sorsix.finkicommunity.models.classes;

import javax.naming.Name;
import javax.persistence.*;

@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "follower_id")
    private long followerId;

    public Follower() {
    }

    public Follower(int userId, int followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public int getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getFollowerId() {
        return followerId;
    }
}
