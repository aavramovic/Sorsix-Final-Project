package com.sorsix.finkicommunity.models.classes;

import com.sorsix.finkicommunity.models.classes.customIdClasses.FollowerCompositeId;

import javax.persistence.*;

@Entity
@Table(name = "followers")
@IdClass(FollowerCompositeId.class)
public class Follower {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name = "follower_id")
    private long followerId;

    public Follower() {
    }

    public Follower(int userId, int followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public long getUserId() {
        return userId;
    }

    public long getFollowerId() {
        return followerId;
    }
}
