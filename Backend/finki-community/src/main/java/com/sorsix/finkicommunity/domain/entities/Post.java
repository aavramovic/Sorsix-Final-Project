package com.sorsix.finkicommunity.domain.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column
    private String content;

    @Column(name="timestamp")
    private LocalDateTime timestamp;

    @Column(name="number_of_likes")
    private int numberOfLikes;

    @Column(name="number_of_replies")
    private int numberOfReplies;

    @ManyToMany(mappedBy = "postsLiked")
    private Set<User> usersLiked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user_id_owner")
    private User user;


    /*
        POST --- repliedBy --- POST     OneToMany
     */
    @OneToMany(
            mappedBy = "repliedTo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Post> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_post_id_repliedTo")
    private Post repliedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_course_id")
    private Course course;

    /*
        USER --- followed by --- USER   ManyToMany
     */
    @ManyToMany(mappedBy = "follows")
    private Set<User> followedBy;


    public Post(){}

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

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<User> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(Set<User> followedBy) {
        this.followedBy = followedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
