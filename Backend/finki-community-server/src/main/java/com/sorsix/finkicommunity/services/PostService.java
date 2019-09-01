package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.NewPostRequest;
import com.sorsix.finkicommunity.domain.responses.exceptions.CourseNotFoundException;
import com.sorsix.finkicommunity.domain.responses.exceptions.UserNotFoundException;
import com.sorsix.finkicommunity.domain.responses.post.PageResponse;
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

    public PostService(PostRepository postRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public PageResponse getAllPosts(Integer noOfPosts, Integer pageNumber, String username) {
        List<Post> posts = postRepository.findPostsByRepliedToIsNullOrderByTimestampDescTitleAsc();
        PageResponse pageResponse = new PageResponse();

        pageResponse.count = posts.size();
        pageResponse.data = convertFromPostToSimplePostResponse(posts, username);

        return pageResponse;
    }

    public List<SimplePostResponse> getTopPosts(Integer noOfPosts, String username) {
//        if (noOfPosts == null || noOfPosts.intValue() == 10) {
//            return getTop10Posts(username);
//        } else if (noOfPosts.intValue() == 25) {
//            return getTop25Posts(username);
//        } else {
//            return getTop50Posts(username);
//        }
        return getAllPosts(username);
    }

    public List<SimplePostResponse> getAllPosts(String username){
        List<Post> posts = postRepository.findPostsByRepliedToIsNullOrderByTimestampDescTitleAsc();
        return convertFromPostToSimplePostResponse(posts, username);
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

    public ClickedPostResponse getClickedPost(Long id, String username) {

        Post post = postRepository.findByPostId(id).get();
        Set<Post> postsLiked = getPostsLikedByUser(username);
        ClickedPostResponse p = new ClickedPostResponse();
        p.setPostResponse(createPostResponseObject(post, postsLiked));

        Set<SimplePostResponse> simplePostResponses = convertFromPostToSimplePostResponseWithSet(post.getReplies(), username);

        p.setReplies(simplePostResponses);

        return p;
    }


    public Post createNewPost(NewPostRequest newPostRequest) throws CourseNotFoundException, UserNotFoundException, Exception {
        Optional<Course> course = courseRepository.findCourseByCourseName(newPostRequest.courseName);

        Optional<User> user = userRepository.findByUsername(newPostRequest.username);

        Post newPost = new Post();
        newPost.setContent(newPostRequest.content);
        newPost.setTitle(newPostRequest.title);

        Post repliedTo = null;
        if(newPostRequest.replyToPostId != null){
            repliedTo = postRepository.findByPostId(newPostRequest.replyToPostId).get();
        }

        course.map(
                c -> {
                    newPost.setCourse(c);
                    return c;
                }
        ).orElseThrow(
                ()->new CourseNotFoundException("No course found with name " + newPostRequest.courseName)
        );

        user.map(
                u -> {
                    newPost.setUser(u);
                    return u;
                }
        ).orElseThrow(
                ()-> new UserNotFoundException("No user with username " + newPostRequest.username)
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


    /*
    HELPER METHODS
     */
    private List<SimplePostResponse> convertFromPostToSimplePostResponse(List<Post> posts, String username) {
        List<SimplePostResponse> postResponses = new ArrayList<>();

        Set<Post> postsLiked = getPostsLikedByUser(username);

        for (Post post : posts) {
            postResponses.add(createPostResponseObject(post, postsLiked));
        }

        return postResponses;
    }

    private SimplePostResponse createPostResponseObject(Post post, Set<Post> postsLiked) {
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
        postResponse.isLiked = postsLiked.contains(post);

        return postResponse;
    }

    private Set<SimplePostResponse> convertFromPostToSimplePostResponseWithSet(Set<Post> replies, String username) {
        Set<SimplePostResponse> postResponses = new TreeSet<>();

        Set<Post> postsLiked = getPostsLikedByUser(username);

        for (Post post : replies) {
            postResponses.add(createPostResponseObject(post, postsLiked));
        }

        return postResponses;
    }

    private Set<Post> getPostsLikedByUser(String username){
        if(username == null)
            return new TreeSet<>();
        return userRepository
                .findByUsername(username)
                .map(
                        user -> user.getPostsLiked()
                )
                .orElse(
                        new TreeSet<>()
                );
    }
}
