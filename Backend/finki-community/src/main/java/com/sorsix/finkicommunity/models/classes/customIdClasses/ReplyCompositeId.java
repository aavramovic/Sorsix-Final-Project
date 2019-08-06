package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;

public class ReplyCompositeId implements Serializable {
    private long basePostId;
    private long replyPostId;

    public ReplyCompositeId() {
    }

    public ReplyCompositeId(long basePostId, long replyPostId) {
        this.basePostId = basePostId;
        this.replyPostId = replyPostId;
    }
}
