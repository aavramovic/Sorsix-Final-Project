package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.Role;
import com.sorsix.finkicommunity.domain.requests.LoginViewModel;
import com.sorsix.finkicommunity.domain.requests.NewFollowingRequest;
import com.sorsix.finkicommunity.domain.requests.NewUserRequest;
import com.sorsix.finkicommunity.domain.responses.user.MockUser;
import com.sorsix.finkicommunity.domain.responses.user.UserResponse;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsFollower;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsFollowing;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsPost;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsResponse;
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

    public User createNewUser(NewUserRequest newUserRequest) {
        User user = new User();

        user.setUsername(newUserRequest.getUsername());
        user.setEmail(newUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
        user.setFirstName(newUserRequest.getFirstName());
        user.setLastName(newUserRequest.getLastName());
        user.setSex(newUserRequest.getSex());
        user.setRole("USER");
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


        if(userFollowing.isPresent() && userFollowed.isPresent()){
            User user1 = userFollowing.get();
            User user2 = userFollowed.get();

            user1.addNewFollowing(user2);

            try{
                userRepository.save(user1);
                return Optional.of(newFollowingRequest);
            }catch(Exception ex){
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public Optional<Set<Post>> getUserPosts(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(
                u -> u.getPosts()
        );
    }

    public UserDetailsResponse getUserDetails(String username){
        User user = userRepository.findByUsername(username);
        if(user == null)
            return null;

        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

        userDetailsResponse.setUserId(user.getUserId());
        userDetailsResponse.setUsername(user.getUsername());
        userDetailsResponse.setEmail(user.getEmail());
        userDetailsResponse.setFirstName(user.getFirstName());
        userDetailsResponse.setLastName(user.getLastName());
        userDetailsResponse.setSex(user.getSex());
        userDetailsResponse.setRole(user.getRole());

        userDetailsResponse.setNumberOfPosts(user.getNumberOfPosts());
        userDetailsResponse.setNumberOfFollowings(user.getNumberOfFollowings());
        userDetailsResponse.setNumberOfFollowers(user.getNumberOfFollowers());

        // POSTS
        Set<Post> userPosts = user.getPosts();
        List<UserDetailsPost> userDetailsPosts = new ArrayList<>();
        UserDetailsPost userDetailsPost;
        for(Post post: userPosts){
            userDetailsPost = new UserDetailsPost();

            userDetailsPost.setId(post.getPostId());
            userDetailsPost.setTimeOfPost(post.getTimestamp());
            userDetailsPost.setCourseName(post.getCourse().getCourseName());
            userDetailsPost.setTitle(post.getTitle());
            userDetailsPost.setContent(post.getContent().substring(0,50));

            userDetailsPosts.add(userDetailsPost);
        }
        userDetailsResponse.setUserDetailsPost(userDetailsPosts);


        // FOLLOWINGS
        Set<User> userFollowings = user.getFollowings();
        List<UserDetailsFollowing> userDetailsFollowings = new ArrayList<>();
        UserDetailsFollowing userDetailsFollowing;
        for(User following: userFollowings){
            userDetailsFollowing = new UserDetailsFollowing();

            userDetailsFollowing.setId(following.getUserId());
            userDetailsFollowing.setUsername(following.getUsername());
            userDetailsFollowing.setFirstName(following.getFirstName());
            userDetailsFollowing.setLastName(following.getLastName());

            userDetailsFollowings.add(userDetailsFollowing);
        }
        userDetailsResponse.setUserDetailsFollowings(userDetailsFollowings);


        // FOLLOWERS
        Set<User> userFollowers = user.getFollowers();
        List<UserDetailsFollower> userDetailsFollowers = new ArrayList<>();
        UserDetailsFollower userDetailsFollower;
        for(User follower: userFollowers){
            userDetailsFollower = new UserDetailsFollower();

            userDetailsFollower.setId(follower.getUserId());
            userDetailsFollower.setUsername(follower.getUsername());
            userDetailsFollower.setFirstName(follower.getFirstName());
            userDetailsFollower.setLastName(follower.getLastName());

            userDetailsFollowers.add(userDetailsFollower);
        }
        userDetailsResponse.setUserDetailsFollowers(userDetailsFollowers);

        return userDetailsResponse;
    }

    public List<MockUser> getAllMockUsers(){
        List<User> users = userRepository.findAll();


        List<MockUser> mockUsers = new ArrayList<>();
        MockUser mockUser;
        for(User user: users){
            mockUser = new MockUser();

            mockUser.setUserId(user.getUserId());
            mockUser.setUsername(user.getUsername());
            mockUser.setFirstName(user.getFirstName());
            mockUser.setLastName(user.getLastName());

            mockUsers.add(mockUser);
        }

        return mockUsers;
    }
}
