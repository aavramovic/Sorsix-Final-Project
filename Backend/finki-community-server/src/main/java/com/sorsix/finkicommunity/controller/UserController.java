package com.sorsix.finkicommunity.controller;

import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.*;
import com.sorsix.finkicommunity.domain.responses.user.FollowResponse;
import com.sorsix.finkicommunity.domain.responses.user.SearchUserResponse;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsResponse;
import com.sorsix.finkicommunity.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forum/users")
@CrossOrigin  // Enabling Cross Origin Requests for a RESTful Web Service
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    GET METHODS
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(
            @RequestParam String username,
            @RequestParam(required = false) String loggedInUsername
    ) {
        return ResponseEntity.ok(userService.getUserDetails(username, loggedInUsername));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/posts")
    public ResponseEntity getUserPosts(@RequestParam Long userId) {
        try{
            return ResponseEntity.ok(userService.getUserPosts(userId));
        }catch(UsernameNotFoundException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchUserResponse>> getResultFromSearch(@RequestParam String q){
        return ResponseEntity.ok(userService.getResultFromSearch(q));
    }

    /*
    POST METHODS
     */
    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        return userService
                .createNewUser(newUserRequest)
                .map(
                        user->ResponseEntity.status(HttpStatus.CREATED).body(user)
                )
                .orElseGet(
                        ()->ResponseEntity.badRequest().build()
                );
    }

    @PostMapping("/follow")
    public ResponseEntity<FollowResponse> addNewFollowing(@RequestBody NewFollowingRequest newFollowingRequest) {
        return userService
                .addNewFollowing(newFollowingRequest)
                .map(
                        res -> ResponseEntity.status(HttpStatus.CREATED).body(res)
                )
                .orElseGet(
                        ()-> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                );
    }

    @PostMapping("/likes")
    public ResponseEntity<NewPostLikeRequest> newPostLike(@RequestBody @Valid NewPostLikeRequest newPostLikeRequest){
        return userService
                .newPostLike(newPostLikeRequest)
                .map(
                        res -> ResponseEntity.status(HttpStatus.CREATED).body(newPostLikeRequest)
                )
                .orElseGet(
                        ()-> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                );
    }

    @PostMapping("/role")
    public ResponseEntity<RoleChangeRequest> changeRole(@RequestBody @Valid RoleChangeRequest roleChangeRequest){
        try{
            return ResponseEntity.ok(userService.changeRole(roleChangeRequest));
        }catch(UsernameNotFoundException ex){
            return ResponseEntity.badRequest().body(roleChangeRequest);
        }

    }


}
