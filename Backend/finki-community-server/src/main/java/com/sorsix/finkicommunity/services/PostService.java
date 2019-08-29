package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.domain.responses.post.MockPost;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import com.sorsix.finkicommunity.domain.responses.post.ClickedPostResponse;
import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

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

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByTimestampDescTitleAsc();
    }

    public List<Post> getAllPostsByUserId(Long userId) {
        if (userRepository.existsById(userId))
            return postRepository.findAllByUser_UserId(userId);
        return null;
    }

    public Post createNewPost(NewPostRequest newPostRequest) {
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

    public List<SimplePostResponse> getTopPosts(Integer noOfPosts, String username) {
        if (noOfPosts == null || noOfPosts.intValue() == 10) {
            return getTop10Posts(username);
        } else if (noOfPosts.intValue() == 25) {
            return getTop25Posts(username);
        } else if (noOfPosts.intValue() == 50) {
            return getTop50Posts(username);
        } else {
            return getTop10Posts(username);
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
        Post post = postRepository.findByPostId(id);

        ClickedPostResponse clickedPostResponse = new ClickedPostResponse();

        clickedPostResponse.setPostResponse(createPostResponseObject(post, null));


        Set<SimplePostResponse> simplePostResponses = convertFromPostToPostResponseSET(post.getReplies());

        clickedPostResponse.setReplies(simplePostResponses);

        return clickedPostResponse;
    }


    /*
    HELPER METHODS
     */
    private SimplePostResponse createPostResponseObject(Post post, String username) {
        SimplePostResponse postResponse = new SimplePostResponse();

        postResponse.setId(post.getPostId());
        postResponse.setContent(post.getContent());
        postResponse.setCourseName(post.getCourse().getCourseName());
        postResponse.setNoOfComments(post.getNumberOfReplies());
        postResponse.setNoOfLikes(post.getNumberOfLikes());
        postResponse.setTimeOfPost(post.getTimestamp());
        postResponse.setTitle(post.getTitle());
        postResponse.setUsername(post.getUser().getUsername());
        postResponse.setSex(post.getUser().getSex());
        postResponse.setRole(post.getUser().getRole());
        if(username==null)
            postResponse.setLiked(false);
        else{
            User user = userRepository.findByUsername(username);
            if(user == null){
                postResponse.setLiked(false);
            }else{
                postResponse.setLiked(user.getPostsLiked().contains(post));
            }
        }

        return postResponse;
    }

    private List<SimplePostResponse> convertFromPostToSimplePostResponse(List<Post> posts, String username) {
        List<SimplePostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            postResponses.add(createPostResponseObject(post, username));
        }

        return postResponses;
    }

    private Set<SimplePostResponse> convertFromPostToPostResponseSET(Set<Post> posts) {
        Set<SimplePostResponse> postResponses = new HashSet<>();

        for (Post post : posts) {
            postResponses.add(createPostResponseObject(post, null));
        }

        return postResponses;
    }

    public List<MockPost> getAllMockPosts() {
        List<Post> posts = postRepository.findAllByOrderByTimestampDescTitleAsc();
        User user = userRepository.findByUsername("fisnik");

        List<MockPost> mockPosts = new ArrayList<>();
        MockPost mockPost;
        for (Post post : posts) {
            mockPost = new MockPost();

            mockPost.id = post.getPostId();
            mockPost.timeOfPost = post.getTimestamp();
            mockPost.noOfLikes = post.getNumberOfLikes();
            mockPost.noOfComments = post.getNumberOfReplies();
            mockPost.title = post.getTitle();
            mockPost.content = post.getContent();

            mockPost.isLiked = user.getPostsLiked().contains(post);

            mockPost.username = post.getUser().getUsername();
            mockPost.courseName = post.getCourse().getCourseName();

            mockPosts.add(mockPost);
        }

        return mockPosts;
    }
}
