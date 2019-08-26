package com.sorsix.finkicommunity.services;

import antlr.Token;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.Role;
import com.sorsix.finkicommunity.domain.requests.LoginViewModel;
import com.sorsix.finkicommunity.domain.requests.NewFollowingRequest;
import com.sorsix.finkicommunity.domain.requests.NewUserRequest;
import com.sorsix.finkicommunity.domain.response.user.MockUser;
import com.sorsix.finkicommunity.domain.response.user.UserResponse;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.security.JwtProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<MockUser> getAllMockUsers(){
        List<User> users = userRepository.findAll();

        List<MockUser> mockUsers = new ArrayList<>();
        MockUser mockUser;
        for(User user: users){
            mockUser = new MockUser();
            mockUser.setUserId(user.getUserId());
            mockUser.setUsername(user.getUsername());

            mockUsers.add(mockUser);
        }

        return mockUsers;
    }

    public User createNewUser(NewUserRequest newUserRequest) {
        User user = new User();

        user.setUsername(newUserRequest.getUsername());
        user.setEmail(newUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        user.setFirstName(newUserRequest.getFirstName());
        user.setLastName(newUserRequest.getLastName());
        user.setBirthdate(newUserRequest.getBirthdate());
        user.setSex(newUserRequest.getSex());
        user.addRole("USER");
        try {
            return userRepository.save(user);
        } catch (RuntimeException ex) {
            return user;
        }
    }

    public UserResponse findExistingUser(LoginViewModel loginViewModel) {
        String encodedPassword;
        String rawPassword;
        UserResponse userResponse = new UserResponse();
        try {
            encodedPassword = userRepository.findByUsername(loginViewModel.getUsername()).getPassword();
            rawPassword = loginViewModel.getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword))
            {
                userResponse.setIdToken(encodedPassword);
                userResponse.setExpiresIn(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME);
                userResponse.setRole(Role.ADMIN);
                userResponse.setValid(true);
            }
            else
            {
                userResponse.setErrorMessage("Incorrect password");
                userResponse.setValid(false);
            }//TODO: check what kind of exceptions?!
        } catch (Exception someException) {
            userResponse.setErrorMessage("Username not found");
            userResponse.setValid(false);
        }
        return userResponse;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<NewFollowingRequest> addNewFollowing(NewFollowingRequest newFollowingRequest) {

        Optional<User> userFollowing = userRepository.findById(newFollowingRequest.getUserIdFollowing());
        Optional<User> userFollowed = userRepository.findById(newFollowingRequest.getUserIdFollowed());

        return userFollowing.map(
                user1 -> Optional.of(newFollowingRequest)
        ).orElseGet(
                () -> Optional.empty()
        );

//        if(userFollowing.isPresent() && userFollowed.isPresent()){
//            User user1 = userFollowing.get();
//            User user2 = userFollowed.get();
//
//            user1.addNewFollowing(user2);
//            userRepository.save(user1);
//            Optional.of(newFollowingRequest);
//        }
//        return Optional.empty();
    }

    public Optional<Set<Post>> getUserPosts(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(
                u -> u.getPosts()
        );
    }


}
