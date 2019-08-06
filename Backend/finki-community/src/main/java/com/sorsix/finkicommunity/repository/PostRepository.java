package com.sorsix.finkicommunity.repository;


import com.sorsix.finkicommunity.models.classes.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop5ByOrderByDateDesc();
}
