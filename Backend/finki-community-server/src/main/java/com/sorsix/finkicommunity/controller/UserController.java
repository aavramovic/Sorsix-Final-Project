package com.sorsix.finkicommunity.controller;

import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.LoginViewModel;
import com.sorsix.finkicommunity.domain.requests.NewFollowingRequest;
import com.sorsix.finkicommunity.domain.requests.NewUserRequest;
import com.sorsix.finkicommunity.domain.responses.user.MockUser;
import com.sorsix.finkicommunity.domain.responses.user.UserResponse;
import com.sorsix.finkicommunity.domain.responses.user_details.UserDetailsResponse;
import com.sorsix.finkicommunity.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/forum/users")
@CrossOrigin(origins = "http://localhost:4200")  // Enabling Cross Origin Requests for a RESTful Web Service
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/details")
    public ResponseEntity<UserDetailsResponse> getUserDetails(
            @RequestParam String username,
            @RequestParam(required = false) String loggedInUsername
    ) {
        // System.out.println(username + " " + loggedInUsername);
        return ResponseEntity.ok(userService.getUserDetails(username, loggedInUsername));
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/posts")
    public ResponseEntity getUserPosts(
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(
                userService.getUserPosts(userId)
        );

    }

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        return ResponseEntity.ok(userService.createNewUser(newUserRequest));
    }

    //TODO: probably not right, change
    @PostMapping("/login")
    public ResponseEntity<UserResponse> findExistingUser(@RequestBody @Valid LoginViewModel loginViewModel) {
        return ResponseEntity.ok(userService.findExistingUser(loginViewModel));
    }

    @PostMapping("/new-following")
    public ResponseEntity<NewFollowingRequest> addNewFollowing(@RequestBody NewFollowingRequest newFollowingRequest) {
        Optional<NewFollowingRequest> result = userService.addNewFollowing(newFollowingRequest);
        // System.out.println(result);
        return ResponseEntity.ok(newFollowingRequest);
//        return userService.addNewFollowing(newFollowingRequest)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/mock")
    public ResponseEntity<List<MockUser>> getAllMockUsers(){
        return ResponseEntity.ok(userService.getAllMockUsers());
    }

}
