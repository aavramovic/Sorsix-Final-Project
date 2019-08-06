package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**Contains the replies commented on posts
 * Post that was replied to ID
 * Post that was replied ID*/

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply getRepliesByBasePostId(long basePostId);
}
