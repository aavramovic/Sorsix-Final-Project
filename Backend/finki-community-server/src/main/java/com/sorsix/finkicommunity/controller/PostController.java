package com.sorsix.finkicommunity.controller;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.domain.responses.exceptions.CourseNotFoundException;
import com.sorsix.finkicommunity.domain.responses.exceptions.UserNotFoundException;
import com.sorsix.finkicommunity.domain.responses.post.ClickedPostResponse;
import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import com.sorsix.finkicommunity.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/forum/posts")
@CrossOrigin(origins = "http://localhost:4200")  // Enabling Cross Origin Requests for a RESTful Web Service
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    /*
    GET METHODS
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/top")
    public ResponseEntity<List<SimplePostResponse>> getTopPosts(
            @RequestParam(required = false) Integer noOfPosts,   // If absent, default will be 10
            @RequestParam(required = false) String username      // if no user logged in, this will be null
    ){
        return ResponseEntity.ok(postService.getTopPosts(noOfPosts, username));
    }

    @GetMapping("/clicked")
    public ResponseEntity<ClickedPostResponse> getClickedPost(
            @RequestParam Long postId,
            @RequestParam String username
    ){
        return ResponseEntity.ok(postService.getClickedPost(postId, username));
    }

    /*
    POST METHODS
     */
    @PostMapping("/new")
    public ResponseEntity<Post> createNewPost(@RequestBody @Valid NewPostRequest newPostRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.createNewPost(newPostRequest));
        }
        catch(CourseNotFoundException | UserNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }


    }
}
