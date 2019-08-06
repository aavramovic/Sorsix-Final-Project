package com.sorsix.finkicommunity.repository;


import com.sorsix.finkicommunity.models.classes.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
