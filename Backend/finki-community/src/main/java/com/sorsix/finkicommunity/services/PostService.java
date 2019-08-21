package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.domain.response.post.ClickedPostResponse;
import com.sorsix.finkicommunity.domain.response.post.SimplePostResponse;
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


    public List<SimplePostResponse> getTopPosts(Integer noOfPosts){
        if(noOfPosts == null || noOfPosts.intValue() == 10){
            return getTop10Posts();
        }
        else if(noOfPosts.intValue() == 25) {
            return getTop25Posts();
        }else if(noOfPosts.intValue() == 50){
            return getTop50Posts();
        }else{
            return getTop10Posts();
        }
    }

    public List<SimplePostResponse> getTop10Posts(){
        List<Post> posts = postRepository.findTop10ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }

    public List<SimplePostResponse> getTop25Posts(){
        List<Post> posts = postRepository.findTop25ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }

    public List<SimplePostResponse> getTop50Posts(){
        List<Post> posts = postRepository.findTop50ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToPostResponse(posts);
    }

    public ClickedPostResponse getClickedPost(Long id){
        Post post = postRepository.findByPostId(id);

        ClickedPostResponse clickedPostResponse = new ClickedPostResponse();

        clickedPostResponse.setPostResponse(createPostResponseObject(post));
        clickedPostResponse.setReplies(post.getReplies());

        return clickedPostResponse;
    }


    /*
    HELPER METHODS
     */
    private SimplePostResponse createPostResponseObject(Post post){
        SimplePostResponse postResponse = new SimplePostResponse();

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

    private List<SimplePostResponse> convertFromPostToPostResponse(List<Post> posts){
        List<SimplePostResponse> postResponses = new ArrayList<SimplePostResponse>();

        for(Post post : posts){
            postResponses.add(createPostResponseObject(post));
        }

        return postResponses;
    }
}
