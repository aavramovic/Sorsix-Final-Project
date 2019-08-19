package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.response.ClickedPostResponse;
import com.sorsix.finkicommunity.response.PostResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private CourseRepository courseRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(
            PostRepository postRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByTimestampDescTitleAsc();
    }

    public List<Post> getAllPostsByUserId(Long userId){
        if(userRepository.existsById(userId))
            return postRepository.findAllByUser_UserId(userId);
        return null;
    }

    public Post createNewPost(NewPostRequest newPostRequest){

        Course course = courseRepository.findById(newPostRequest.getCourseId()).get();
        Post repliedTo = null;
        User user = userRepository.findById(newPostRequest.getUserIdOwner()).get();

        Post newPost = new Post();
        newPost.setContent(newPostRequest.getContent());
        newPost.setCourse(course);
        newPost.setUser(user);
        newPost.setRepliedTo(repliedTo);

        newPost.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        newPost.setNumberOfLikes(0);
        newPost.setNumberOfReplies(0);

        postRepository.save(newPost);
        return newPost;
    }

    public Post getTopPost(){
        return postRepository.findTopByOrderByTimestampDescTitleAsc();
    }

    public List<PostResponse> getTop10Posts(){
        List<Post> posts = postRepository.findTop10ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }

    public List<PostResponse> getTop25Posts(){
        List<Post> posts = postRepository.findTop25ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }

    public List<PostResponse> getTop50Posts(){
        List<Post> posts = postRepository.findTop50ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }


    private List<PostResponse> convertFromPostToPostResponse(List<Post> posts){
        List<PostResponse> postResponses = new ArrayList<PostResponse>();


        for(Post post : posts){
            postResponses.add(createPostResponseObject(post));
        }

        return postResponses;
    }


    public ClickedPostResponse getClickedPost(Long id){
        Post post = postRepository.findByPostId(id);

        ClickedPostResponse clickedPostResponse = new ClickedPostResponse();

        clickedPostResponse.setPostResponse(createPostResponseObject(post));
        clickedPostResponse.setReplies(post.getReplies());

        return clickedPostResponse;
    }

    private PostResponse createPostResponseObject(Post post){

        PostResponse postResponse = new PostResponse();

        postResponse.setId(post.getPostId());
        postResponse.setContent(post.getContent());
        postResponse.setCourseName(post.getCourse().getCourseName());
        postResponse.setNoOfComments(post.getNumberOfReplies());
        postResponse.setNoOfLikes(post.getNumberOfLikes());
        postResponse.setTimeOfPost(post.getTimestamp());
        postResponse.setTitle(post.getTitle());
        postResponse.setUsername(post.getUser().getUsername());

        return postResponse;
    }
}
