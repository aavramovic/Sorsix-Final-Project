package com.sorsix.finkicommunity.domain.entities.composite_ids;

import java.io.Serializable;
import java.util.Objects;

public class LikeId implements Serializable {
    private long postId;
    private long userId;

    public LikeId() {
    }

    public LikeId(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId that = (LikeId) o;
        return postId == that.postId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }
}
