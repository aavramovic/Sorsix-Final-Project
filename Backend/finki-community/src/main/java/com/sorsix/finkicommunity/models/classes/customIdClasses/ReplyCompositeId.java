package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;
import java.util.Objects;

public class ReplyCompositeId implements Serializable {
    private long basePostId;
    private long replyPostId;

    public ReplyCompositeId() {
    }

    public ReplyCompositeId(long basePostId, long replyPostId) {
        this.basePostId = basePostId;
        this.replyPostId = replyPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyCompositeId that = (ReplyCompositeId) o;
        return basePostId == that.basePostId &&
                replyPostId == that.replyPostId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePostId, replyPostId);
    }
}
