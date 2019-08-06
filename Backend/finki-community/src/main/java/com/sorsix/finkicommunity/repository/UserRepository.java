package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.models.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserId(long userId);

    User getUserByEmail(String email);

    User getUserByFirstNameAndLastName(String firstName, String lastName);
}
