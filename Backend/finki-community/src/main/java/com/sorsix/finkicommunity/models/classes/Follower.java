package com.sorsix.finkicommunity.models.classes;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Follower {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "follower_id")
    private int followerId;

    public Follower() {
    }

    public Follower(int userId, int followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }


}
