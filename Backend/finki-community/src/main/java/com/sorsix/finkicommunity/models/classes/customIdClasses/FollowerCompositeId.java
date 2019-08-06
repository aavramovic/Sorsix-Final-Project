package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;
import java.util.Objects;

public class FollowerCompositeId implements Serializable {
    private long userId;
    private long followerId;

    public FollowerCompositeId() {
    }

    public FollowerCompositeId(long userId, long followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowerCompositeId that = (FollowerCompositeId) o;
        return userId == that.userId &&
                followerId == that.followerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, followerId);
    }
}
