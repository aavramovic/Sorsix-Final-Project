package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.errors.NewFollowingRequest;
import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(User newUser){
        return userRepository.save(newUser);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public boolean addNewFollowing(NewFollowingRequest newFollowingRequest){
        User userFollowing = userRepository.findById(newFollowingRequest.getUserIdFollowing()).get();
        User userFollowed = userRepository.findById(newFollowingRequest.getUserIdFollowed()).get();

        System.out.println(userFollowing);
        System.out.println(userFollowed);

        userFollowing.addNewFollowing(userFollowed);
        userRepository.saveAndFlush(userFollowed);

        return true;
    }
}
