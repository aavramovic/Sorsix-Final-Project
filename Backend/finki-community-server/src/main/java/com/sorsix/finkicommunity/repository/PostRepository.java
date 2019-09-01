package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByRepliedToIsNullOrderByTimestampDescTitleAsc();
    List<Post> findTop10ByRepliedToIsNullOrderByTimestampDescTitleAsc();
    List<Post> findTop25ByRepliedToIsNullOrderByTimestampDescTitleAsc();
    List<Post> findTop50ByRepliedToIsNullOrderByTimestampDescTitleAsc();
    Optional<Post> findByPostId(Long postId);
}
