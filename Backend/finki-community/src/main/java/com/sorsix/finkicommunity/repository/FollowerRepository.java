package com.sorsix.finkicommunity.repository;


import com.sorsix.finkicommunity.domain.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
