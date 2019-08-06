package com.sorsix.finkicommunity.domain.entities.composite_ids;

import java.io.Serializable;
import java.util.Objects;

public class ReplyId implements Serializable {
    private long basePostId;
    private long replyPostId;

    public ReplyId() {
    }

    public ReplyId(long basePostId, long replyPostId) {
        this.basePostId = basePostId;
        this.replyPostId = replyPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyId that = (ReplyId) o;
        return basePostId == that.basePostId &&
                replyPostId == that.replyPostId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePostId, replyPostId);
    }
}
