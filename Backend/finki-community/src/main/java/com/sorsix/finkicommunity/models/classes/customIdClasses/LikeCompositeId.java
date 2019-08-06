package com.sorsix.finkicommunity.models.classes.customIdClasses;

import java.io.Serializable;

public class LikeCompositeId implements Serializable {
    private long postId;
    private long userId;

    public LikeCompositeId() {
    }

    public LikeCompositeId(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

}
