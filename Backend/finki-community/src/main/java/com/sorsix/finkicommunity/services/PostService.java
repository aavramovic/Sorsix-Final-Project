package com.sorsix.finkicommunity.services;


import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.requests.errors.NewPostRequest;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.repository.PostRepository;
import com.sorsix.finkicommunity.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
        return postRepository.findAll();
    }

    public List<Post> getAllPostsByUserId(Long userId){
        if(userRepository.existsById(userId))
            return postRepository.findAllByUser_UserId(userId);
        return null;
    }

    public User createNewPost(NewPostRequest newPostRequest){

        Course course = courseRepository.findById(newPostRequest.getCourseId()).get();
        Post repliedTo = null;
        if(newPostRequest.getPostIdRepliedTo() != null){
             repliedTo = postRepository.findById(newPostRequest.getPostIdRepliedTo()).get();
        }
        User user = userRepository.findById(newPostRequest.getUserIdOwner()).get();

        Post newPost = new Post();
        newPost.setContent(newPostRequest.getContent());
        newPost.setCourse(course);
        newPost.setUser(user);
        newPost.setRepliedTo(repliedTo);

        newPost.setTimestamp(LocalDateTime.now());
        newPost.setNumberOfLikes(0);
        newPost.setNumberOfReplies(0);

        user.addNewPost(newPost);
        return user;
    }

}
