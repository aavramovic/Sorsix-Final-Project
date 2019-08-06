package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;

public class FollowerCompositeId implements Serializable {
    private long userId;
    private long followerId;

    public FollowerCompositeId() {
    }

    public FollowerCompositeId(long userId, long followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }
}
