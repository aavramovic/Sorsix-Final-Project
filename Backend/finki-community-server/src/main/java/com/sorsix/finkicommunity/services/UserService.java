package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.Role;
import com.sorsix.finkicommunity.domain.requests.*;
import com.sorsix.finkicommunity.domain.responses.user.SearchUserResponse;
import com.sorsix.finkicommunity.domain.responses.user.UserResponse;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsFollow;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsPost;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsResponse;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.security.JwtProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) { return userRepository.findById(id);}

    public Set<Post> getUserPosts(Long id) throws UsernameNotFoundException{
        return userRepository
                .findById(id)
                .map(
                        user -> user.getPosts()
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException("No user found with id " + id)
                );
    }

    public UserDetailsResponse getUserDetails(String username, String loggedInUsername) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(
                        user -> {
                            UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

                            userDetailsResponse.userId = user.getUserId();
                            userDetailsResponse.username = user.getUsername();
                            userDetailsResponse.email = user.getEmail();
                            userDetailsResponse.firstName = user.getFirstName();
                            userDetailsResponse.lastName = user.getLastName();
                            userDetailsResponse.sex = user.getSex();
                            userDetailsResponse.birthdate = user.getBirthdate();
                            userDetailsResponse.role = user.getRole();

                            userDetailsResponse.numberOfPosts = user.getNumberOfPosts();
                            userDetailsResponse.numberOfFollowings = user.getNumberOfFollowings();
                            userDetailsResponse.numberOfFollowers = user.getNumberOfFollowers();

                            // POSTS
                            Set<Post> userPosts = user.getPosts();
                            List<UserDetailsPost> userDetailsPosts = new ArrayList<>();
                            UserDetailsPost userDetailsPost;
                            for (Post post : userPosts) {
                                userDetailsPost = new UserDetailsPost();

                                userDetailsPost.id = post.getPostId();
                                userDetailsPost.timeOfPost = post.getTimestamp();
                                userDetailsPost.courseName = post.getCourse().getCourseName();
                                userDetailsPost.title = post.getTitle();
                                userDetailsPost.content = post.getContent().substring(0, 50);

                                userDetailsPosts.add(userDetailsPost);
                            }
                            userDetailsResponse.userDetailsPosts = userDetailsPosts;

                            // POSTS LIKED
                            Set<Post> userPostsLiked = user.getPostsLiked();

                            userDetailsResponse.numberOfPostsLiked = userPostsLiked.size();

                            List<UserDetailsPost> userDetailsPostsLiked = new ArrayList<>();
                            UserDetailsPost userDetailsPostLiked;
                            for (Post postLiked : userPostsLiked) {
                                userDetailsPostLiked = new UserDetailsPost();

                                userDetailsPostLiked.id = postLiked.getPostId();
                                userDetailsPostLiked.timeOfPost = postLiked.getTimestamp();
                                userDetailsPostLiked.courseName = postLiked.getCourse().getCourseName();
                                userDetailsPostLiked.title = postLiked.getTitle();
                                userDetailsPostLiked.content = postLiked.getContent().substring(0, 50);

                                userDetailsPostsLiked.add(userDetailsPostLiked);
                            }
                            userDetailsResponse.userDetailsPostsLiked = userDetailsPostsLiked;

                            // FOLLOWINGS
                            Set<User> userFollowings = user.getFollowings();
                            List<UserDetailsFollow> userDetailsFollows = new ArrayList<>();
                            UserDetailsFollow userDetailsFollow;
                            for (User following : userFollowings) {
                                userDetailsFollow = new UserDetailsFollow();

                                userDetailsFollow.id = following.getUserId();
                                userDetailsFollow.username = following.getUsername();
                                userDetailsFollow.firstName = following.getFirstName();
                                userDetailsFollow.lastName = following.getLastName();

                                userDetailsFollows.add(userDetailsFollow);
                            }
                            userDetailsResponse.userDetailsFollowings = userDetailsFollows;


                            // FOLLOWERS
                            Set<User> userFollowers = user.getFollowers();
                            List<UserDetailsFollow> userDetailsFollowers = new ArrayList<>();
                            UserDetailsFollow userDetailsFollower;
                            for (User follower : userFollowers) {
                                userDetailsFollower = new UserDetailsFollow();

                                userDetailsFollower.id = follower.getUserId();
                                userDetailsFollower.username = follower.getUsername();
                                userDetailsFollower.firstName = follower.getFirstName();
                                userDetailsFollower.lastName = follower.getLastName();

                                userDetailsFollowers.add(userDetailsFollower);
                            }
                            userDetailsResponse.userDetailsFollowers = userDetailsFollowers;

                            if (loggedInUsername != null) {
                                userRepository
                                        .findByUsername(loggedInUsername)
                                        .map(
                                                userLogged -> {
                                                    if (userLogged.getFollowings().contains(user)) {
                                                        userDetailsResponse.isFollowing = true;
                                                    }
                                                    return userLogged;
                                                }
                                        )
                                        .orElseThrow(
                                                ()->new UsernameNotFoundException("No user found with username " + loggedInUsername)
                                        );

                            }
                            return userDetailsResponse;
                        })
                .orElseThrow(
                        ()->new UsernameNotFoundException("No user found with username " + loggedInUsername)
                );
    }

    public UserResponse findExistingUser(LoginViewModel loginViewModel){
        String encodedPassword;
        String rawPassword;
        UserResponse userResponse = new UserResponse();
        Optional<User> user;
        try {
            user = userRepository.findByUsername(loginViewModel.getUsername());
            encodedPassword = user
                    .map(
                            u -> u.getPassword()
                    ).orElseThrow(
                            () -> new UsernameNotFoundException("No user found with username " + loginViewModel.getUsername())
                    );

            rawPassword = loginViewModel.getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                userResponse.setIdToken(encodedPassword);
                userResponse.setExpiresIn(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME);
                user
                        .map(u -> {
                            userResponse.setRole(u.getRole());
                            return u;
                        })
                        .orElseThrow(() -> new RuntimeException());
                userResponse.setUsername(loginViewModel.getUsername());
                userResponse.setValid(true);
            } else {
                userResponse.setErrorMessage("Incorrect password");
                userResponse.setValid(false);
            }
        } catch (Exception someException) {
            userResponse.setErrorMessage("Username not found");
            userResponse.setValid(false);
        }
        return userResponse;
    }

    public Optional<User> createNewUser(NewUserRequest newUserRequest) {
        User user = new User();

        user.setUsername(newUserRequest.username);
        user.setEmail(newUserRequest.email);
        user.setPassword(passwordEncoder.encode(newUserRequest.password));
        user.setFirstName(newUserRequest.firstName);
        user.setLastName(newUserRequest.lastName);
        user.setSex(newUserRequest.sex);
        user.setBirthdate(newUserRequest.birthdate);
        user.setRole(Role.USER);

        try {
            return Optional.of(userRepository.save(user));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    public Optional<?> addNewFollowing(NewFollowingRequest newFollowingRequest) {
        return userRepository
                .findByUsername(newFollowingRequest.usernameFollowing)
                .map(
                        userFollowing ->{
                            return userRepository
                                    .findById(newFollowingRequest.userIdFollowed)
                                    .map(
                                            userFollowed -> {
                                                if(userFollowing.getFollowings().contains(userFollowed)) {
                                                    userFollowing.removeFollowing(userFollowed);
                                                }else{
                                                    userFollowing.addNewFollowing(userFollowed);
                                                }
                                                try {
                                                    userRepository.save(userFollowing);
                                                    userRepository.save(userFollowed);
                                                    return Optional.of(newFollowingRequest);
                                                }catch(Exception ex) {
                                                    return Optional.empty();
                                                }
                                            }
                                    )
                                    .orElseGet(()->Optional.empty());
                        }
                )
                .orElseGet(()->Optional.empty());
    }

    public Optional<NewPostLikeRequest> newPostLike(NewPostLikeRequest newPostLikeRequest) {
//        try {
//            userRepository
//                    .findByUsername(newPostLikeRequest.username)
//                    .map(
//                            user ->
//                            {
//                                try {
//                                    return postRepository
//                                            .findByPostId(newPostLikeRequest.postId)
//                                            .map(
//                                                    post -> {
//                                                        if (user.getPostsLiked().contains(post)) {
//                                                            user.removePostLiked(post);
//                                                        } else {
//                                                            user.addPostLiked(post);
//                                                        }
//
//                                                        userRepository.save(user);
//                                                        postRepository.save(post);
//                                                        return Optional.of(newPostLikeRequest);
//                                                    }
//                                            ).orElseThrow
//                                                    (
//                                                            () -> new PostNotFoundException("No post found with post id: " + newPostLikeRequest.postId)
//                                                    );
//                                } catch (PostNotFoundException e) {
//                                    return Optional.empty();
//                                }
//                            }
//                    )
//                    .orElseThrow(
//                            () -> new UsernameNotFoundException("No user found with username: " + newPostLikeRequest.username)
//                    );
//        }catch(UsernameNotFoundException e){
//            return Optional.empty();
//        }
        return Optional.empty();
    }

    public List<SearchUserResponse> getResultFromSearch(String q){
        List<User> result = userRepository.findByUsernameContainingOrderByUsername(q);

        List<SearchUserResponse> resultUsers = new ArrayList<>();
        SearchUserResponse searchUserResponse;
        for(User user : result){
            searchUserResponse = new SearchUserResponse();

            searchUserResponse.userId = user.getUserId();
            searchUserResponse.username = user.getUsername();
            searchUserResponse.firstName = user.getFirstName();
            searchUserResponse.lastName = user.getLastName();
            searchUserResponse.role = user.getRole().toString();

            resultUsers.add(searchUserResponse);
        }
        return resultUsers;
    }

    public RoleChangeRequest changeRole(RoleChangeRequest roleChangeRequest) throws UsernameNotFoundException{
            return userRepository
                    .findByUsername(roleChangeRequest.username)
                    .map(
                            user -> {
                                user.setRole(roleChangeRequest.role);
                                return roleChangeRequest;
                            }
                    )
                    .orElseThrow(
                            () -> new UsernameNotFoundException("No user found with username " + roleChangeRequest.username)
                    );
    }
}
