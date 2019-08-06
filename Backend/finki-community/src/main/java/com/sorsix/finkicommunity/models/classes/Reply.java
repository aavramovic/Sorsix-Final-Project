package com.sorsix.finkicommunity.models.classes;

import com.sorsix.finkicommunity.models.classes.customIdClasses.ReplyCompositeId;

import javax.persistence.*;
import java.io.Serializable;

/**@Id should be a combination on
 * Long basePostID (Id of the post that was replied to)
 * Long replyPostID (Id of the reply post)
 * */
@Entity
@Table(name="replies")
@IdClass(ReplyCompositeId.class)
public class Reply implements Serializable {
    @Id
    @Column(name="base_post_id")
    private long basePostId;
    @Id
    @Column(name="reply_post_id")
    private long replyPostId;

    public Reply() {}

    public Reply(long basePostId, long replyPostId) {
        this.basePostId = basePostId;
        this.replyPostId = replyPostId;
    }

    public long getBasePostId() {
        return basePostId;
    }

    public long getReplyPostId() {
        return replyPostId;
    }
}
