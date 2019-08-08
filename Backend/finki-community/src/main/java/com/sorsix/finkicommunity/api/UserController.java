package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forum/users")
@CrossOrigin(origins = "http://localhost:4200")  // Enabling Cross Origin Requests for a RESTful Web Service
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<User> createNewUser(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.createNewUser(user));
    }
}
