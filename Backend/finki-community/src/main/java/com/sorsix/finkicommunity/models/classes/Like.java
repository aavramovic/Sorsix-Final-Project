package com.sorsix.finkicommunity.models.classes;

import com.sorsix.finkicommunity.models.classes.customIdClasses.LikeCompositeId;

import javax.persistence.*;
import java.io.Serializable;

/**Who liked the post
 * Which post*/
@Entity
@Table(name="likes")
@IdClass(LikeCompositeId.class)
public class Like implements Serializable {
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
