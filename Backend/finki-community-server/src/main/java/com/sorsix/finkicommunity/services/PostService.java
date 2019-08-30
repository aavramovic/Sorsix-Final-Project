package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.domain.responses.exceptions.CourseNotFoundException;
import com.sorsix.finkicommunity.domain.responses.exceptions.UserNotFoundException;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.domain.responses.post.ClickedPostResponse;
import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class PostService {
    private CourseRepository courseRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByTimestampDescTitleAsc();
    }

    public List<SimplePostResponse> getTopPosts(Integer noOfPosts, String username) {
        if (noOfPosts == null || noOfPosts.intValue() == 10) {
            return getTop10Posts(username);
        } else if (noOfPosts.intValue() == 25) {
            return getTop25Posts(username);
        } else{
            return getTop50Posts(username);
        }
    }

    private List<SimplePostResponse> getTop10Posts(String username) {
        List<Post> posts = postRepository.findTop10ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToSimplePostResponse(posts, username);
    }

    private List<SimplePostResponse> getTop25Posts(String username) {
        List<Post> posts = postRepository.findTop25ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToSimplePostResponse(posts, username);
    }

    private List<SimplePostResponse> getTop50Posts(String username) {
        List<Post> posts = postRepository.findTop50ByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToSimplePostResponse(posts, username);
    }

    public ClickedPostResponse getClickedPost(Long id) {
//        Post post = postRepository.findByPostId(id);
//
//        ClickedPostResponse clickedPostResponse = new ClickedPostResponse();
//
//        clickedPostResponse.setPostResponse(createPostResponseObject(post, null));
//
//
//        Set<SimplePostResponse> simplePostResponses = convertFromPostToPostResponseSET(post.getReplies());
//
//        clickedPostResponse.setReplies(simplePostResponses);
//
//        return clickedPostResponse;
        return null;
    }


    /*
    HELPER METHODS
     */
    private List<SimplePostResponse> convertFromPostToSimplePostResponse(List<Post> posts, String username) {
        List<SimplePostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            postResponses.add(createPostResponseObject(post, username));
        }

        return postResponses;
    }

    private SimplePostResponse createPostResponseObject(Post post, String username) {
        SimplePostResponse postResponse = new SimplePostResponse();

        postResponse.id = post.getPostId();
        postResponse.content = post.getContent();
        postResponse.courseName = post.getCourse().getCourseName();
        postResponse.noOfComments = post.getNumberOfReplies();
        postResponse.noOfLikes = post.getNumberOfLikes();
        postResponse.timeOfPost = post.getTimestamp();
        postResponse.title = post.getTitle();
        postResponse.username = post.getUser().getUsername();
        postResponse.sex = post.getUser().getSex();
        postResponse.role = post.getUser().getRole();

        if(username==null) // Means that there is a visitor (not logged in user)
            postResponse.isLiked = false;
        else{
            postResponse.isLiked =
                    userRepository
                            .findByUsername(username)
                            .map(
                                    user -> user.getPostsLiked().contains(post)
                            )
                            .orElse(
                                    false
                            );


        }

        return postResponse;
    }

    public Post createNewPost(NewPostRequest newPostRequest) throws CourseNotFoundException, UserNotFoundException, Exception {
        Optional<Course> course = courseRepository.findById(newPostRequest.courseId);
        Post repliedTo = null;
        Optional<User> user = userRepository.findById(newPostRequest.userIdOwner);

        Post newPost = new Post();
        newPost.setContent(newPostRequest.content);

        course.map(
                c -> {
                    newPost.setCourse(c);
                    return c;
                }
        ).orElseThrow(
                ()->new CourseNotFoundException("No course found with id " + newPostRequest.courseId)
        );

        user.map(
                u -> {
                    newPost.setUser(u);
                    return u;
                }
        ).orElseThrow(
                ()-> new UserNotFoundException("No user with id " + newPostRequest.userIdOwner)
        );

        newPost.setRepliedTo(repliedTo);
        newPost.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        newPost.setNumberOfLikes(0);
        newPost.setNumberOfReplies(0);
        try{
            postRepository.save(newPost);
        }catch(Exception ex){
            throw ex;
        }

        return newPost;
    }
}
