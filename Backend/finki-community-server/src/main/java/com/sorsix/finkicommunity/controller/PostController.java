package com.sorsix.finkicommunity.controller;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.requests.NewPostLikeRequest;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.domain.responses.post.ClickedPostResponse;
import com.sorsix.finkicommunity.domain.responses.post.MockPost;
import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import com.sorsix.finkicommunity.services.PostService;
import org.apache.coyote.Response;
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


    // GET METHODS
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/top")
    public ResponseEntity<List<SimplePostResponse>> getTopPosts(
            @RequestParam(required = false) Integer noOfPosts,   // If absent, default will be 10
            @RequestParam(required = false) String username
    ){
        return ResponseEntity.ok(postService.getTopPosts(noOfPosts, username));
    }

    @GetMapping("/clicked")
    public ResponseEntity<ClickedPostResponse> getClickedPost(
            @RequestParam Long postId
    ){
        return ResponseEntity.ok(postService.getClickedPost(postId));
    }

    // POST METHODS
    @PostMapping("/new")
    public ResponseEntity<Post> createNewPost(@RequestBody @Valid NewPostRequest newPostRequest){
        return ResponseEntity.ok(postService.createNewPost(newPostRequest));

    }

    @GetMapping("/mock")
    public ResponseEntity<List<MockPost>> getAllMockPosts(){
        return ResponseEntity.ok(postService.getAllMockPosts());
    }
}
