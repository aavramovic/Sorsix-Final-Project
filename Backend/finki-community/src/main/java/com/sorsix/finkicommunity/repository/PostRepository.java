package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByTimestampDescTitleAsc();
    List<Post> findAllByUser_UserId(Long id);
    Post findTopByOrderByTimestampDescTitleAsc();
    List<Post> findByRepliedToIsNullOrderByTimestampDesc();
    List<Post> findTop10ByRepliedToIsNullOrderByTimestampDescTitleAsc();
    List<Post> findTop25ByRepliedToIsNullOrderByTimestampDescTitleAsc();
    List<Post> findTop50ByRepliedToIsNullOrderByTimestampDescTitleAsc();

    Post findByPostId(Long postId);
}
