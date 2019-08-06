package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.entities.composite_ids.LikeId;

import javax.persistence.*;

/**Who liked the post
 * Which post*/
@Entity
@Table(name="likes")
@IdClass(LikeId.class)
public class Like {
    @Id
    @Column(name = "post_id")
    private long postId;
    @Id
    @Column(name = "user_id")
    private long userId;

    public Like(){}

    public Like(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }
}
