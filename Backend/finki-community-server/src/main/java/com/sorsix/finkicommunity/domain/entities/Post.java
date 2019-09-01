package com.sorsix.finkicommunity.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Entity
@Table(name="posts")
public class Post implements Comparable<Post>{

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column
    private String title;

    @Column(length = 1000)
    private String content;

    @Column(name="timestamp")
    private long timestamp;

    @Column(name="number_of_likes")
    private int numberOfLikes = 0;

    @Column(name="number_of_replies")
    private int numberOfReplies = 0;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "postsLiked",
            fetch = FetchType.EAGER
    )
    private Set<User> usersLiked = new HashSet<>();

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "fk_user_id_owner")
    private User user;

    /*
        POST --- repliedBy --- POST     OneToMany
     */
    @JsonIgnore
    @OneToMany(
            mappedBy = "repliedTo",
            fetch = FetchType.EAGER
    )
    private Set<Post> replies = new TreeSet<>();

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "fk_post_id_repliedTo")
    private Post repliedTo;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "fk_course_id")
    private Course course;


    public Post(){}

    // IF POST IS NEW
    public Post(String title, String content, User user, Course course) {
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.user = user;
        this.repliedTo = null;
        this.course = course;

        this.user.incrementNumberOfPosts();
        this.course.incrementNumberOfPosts();
    }

    // IF POST IS A REPLY
    public Post(String title, String content, User user, Post repliedTo) {
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.user = user;
        this.repliedTo = repliedTo;
        this.course = repliedTo.getCourse();

        this.repliedTo.incrementNumberOfReplies();
        this.user.incrementNumberOfPosts();
        this.repliedTo.getCourse().incrementNumberOfReplies();
    }


    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(int numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public Set<User> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(Set<User> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }

    public Post getRepliedTo() {
        return repliedTo;
    }

    public void setRepliedTo(Post repliedTo) {
        this.repliedTo = repliedTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void incrementNumberOfLikes(){
        numberOfLikes++;
    }

    public void decrementNumberOfLikes(){
        numberOfLikes--;
    }

    public void incrementNumberOfReplies(){
        numberOfReplies++;
    }

    public void decrementNumberOfReplies(){
        numberOfReplies--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return postId == post.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public int compareTo(Post o) {
        if(timestamp > o.timestamp)
            return -1;
        return 1;
    }
}
