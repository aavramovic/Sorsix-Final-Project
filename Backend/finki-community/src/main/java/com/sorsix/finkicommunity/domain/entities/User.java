package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.Authority;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="birthdate")
    private Date birthdate;

    @Column(name = "password")
    private String password;

    @Column(name = "number_posts")
    private int numberOfPosts;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "authority")
    private Authority authority;

    /*
        USER --- follows --- USER     ManyToMany
     */
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name="user_follows_user",
            joinColumns = @JoinColumn(name="user_id_following"),
            inverseJoinColumns = @JoinColumn(name = "user_id_followed")
    )
    private Set<User> follows;


    /*
        USER --- followed by --- USER   ManyToMany
     */
    @ManyToMany(mappedBy = "follows")
    private Set<User> followedBy;


    /*
        USER --- posts --- POST     OneToMany
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Post> posts;

    /*
        USER --- likes --- POST     ManyToMany
     */
    @ManyToMany
    @JoinTable(
            name="user_likes_post",
            joinColumns = @JoinColumn(name = "fk_user_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_post_id")
    )
    private Set<Post> postsLiked;


    /*
        This is used by JACKSON JSON when converts the POJO to json
     */

    public User() {
    }

    public User(String firstName, String lastName, Date birthdate, String password, int numberOfPosts, String email, String pictureUrl, Authority authority, Set<User> follows, Set<User> followedBy, Set<Post> posts, Set<Post> postsLiked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.password = password;
        this.numberOfPosts = numberOfPosts;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.authority = authority;
        this.follows = follows;
        this.followedBy = followedBy;
        this.posts = posts;
        this.postsLiked = postsLiked;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public boolean addNewFollowing(User newFollowing){
        return follows.add(newFollowing);
    }

    public boolean removeFollowing(User following){
        return follows.remove(following);
    }

    public boolean addNewPost(Post newPost){
        return posts.add(newPost);
    }

    public boolean removePost(Post post){
        return posts.remove(post);
    }

    public boolean addPostLiked(Post newPostLiked){
         return postsLiked.add(newPostLiked);
    }
}
