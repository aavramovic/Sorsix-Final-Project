package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.models.classes.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**Contains the replies commented on posts
 * Post that was replied to ID
 * Post that was replied ID*/
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply getRepliesByBasePostId(long basePostId);
}
