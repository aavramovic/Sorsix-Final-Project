package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserId(long userId);

    User getUserByEmail(String email);

    User getUserByFirstNameAndLastName(String firstName, String lastName);
}
