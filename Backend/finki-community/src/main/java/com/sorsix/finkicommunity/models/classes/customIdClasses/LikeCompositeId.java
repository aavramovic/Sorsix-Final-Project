package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;
import java.util.Objects;

public class LikeCompositeId implements Serializable {
    private long postId;
    private long userId;

    public LikeCompositeId() {
    }

    public LikeCompositeId(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeCompositeId that = (LikeCompositeId) o;
        return postId == that.postId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }
}
