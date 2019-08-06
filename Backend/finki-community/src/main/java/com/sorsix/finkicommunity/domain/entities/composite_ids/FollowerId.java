package com.sorsix.finkicommunity.domain.entities.composite_ids;

import java.io.Serializable;
import java.util.Objects;

public class FollowerId implements Serializable {
    private long userId;
    private long followerId;

    public FollowerId() {
    }

    public FollowerId(long userId, long followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowerId that = (FollowerId) o;
        return userId == that.userId &&
                followerId == that.followerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, followerId);
    }
}
