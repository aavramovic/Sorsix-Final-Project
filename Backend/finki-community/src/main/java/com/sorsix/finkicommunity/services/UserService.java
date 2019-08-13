package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.Authority;
import com.sorsix.finkicommunity.domain.requests.NewFollowingRequest;
import com.sorsix.finkicommunity.domain.requests.NewUserRequest;
import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createNewUser(NewUserRequest newUserRequest){
        User user = new User();

        user.setEmail(newUserRequest.getEmail());
        user.setPassword(newUserRequest.getPassword());
        user.setFirstName(newUserRequest.getFirstName());
        user.setLastName(newUserRequest.getLastName());

        String[] date = newUserRequest.getBirthdate().split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        user.setBirthdate(LocalDate.of(year, month, day));

        user.setNumberOfPosts(0);
        user.setAuthority(Authority.REGULAR);
        try{
            return userRepository.save(user);
        }catch(RuntimeException ex){
            return null;
        }

    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Integer addNewFollowing(NewFollowingRequest newFollowingRequest){
        User userFollowing = userRepository.findById(newFollowingRequest.getUserIdFollowing()).get();
        User userFollowed = userRepository.findById(newFollowingRequest.getUserIdFollowed()).get();

        userFollowing.addNewFollowing(userFollowed);
        userRepository.save(userFollowing);
        return userFollowing.getFollows().size();
    }
}
