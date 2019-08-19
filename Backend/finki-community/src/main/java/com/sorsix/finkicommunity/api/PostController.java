package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.response.ClickedPostResponse;
import com.sorsix.finkicommunity.response.PostResponse;
import com.sorsix.finkicommunity.services.PostService;
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
    USED ONLY FOR TESTING
     */
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /*
    USED ONLY FOR TESTING
     */
//    @GetMapping("/{userId}")
//    public ResponseEntity getAllPostsByUserId(@PathVariable Long userId){
//        List<Post> result = postService.getAllPostsByUserId(userId);
//        if(result == null){
//            return ResponseEntity.badRequest().body("There is no user with id = " + userId);
//        }
//        else{
//            return ResponseEntity.ok(result);
//        }
//    }

    @GetMapping("/top/10")
    public ResponseEntity<List<PostResponse>> getTop10Posts(){
        return ResponseEntity.ok(postService.getTop10Posts());
    }

    @GetMapping("/top/25")
    public ResponseEntity<List<PostResponse>> getTop25Posts(){
        return ResponseEntity.ok(postService.getTop25Posts());
    }

    @GetMapping("/top/50")
    public ResponseEntity<List<PostResponse>> getTop50Posts(){
        return ResponseEntity.ok(postService.getTop50Posts());
    }

    @PostMapping("/new")
    public ResponseEntity<Post> createNewPost(@RequestBody @Valid NewPostRequest newPostRequest){
        return ResponseEntity.ok(postService.createNewPost(newPostRequest));

    }

    @GetMapping("/clicked")
    public ResponseEntity<ClickedPostResponse> getClickedPost(
            @RequestParam Long postId
    ){
        return ResponseEntity.ok(postService.getClickedPost(postId));
    }
}
