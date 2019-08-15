package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.Authority;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique=true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private long birthdate;

    @Column(name = "posts_number")
    private int numberOfPosts = 0;

    @Column(name = "picture_url")
    private String pictureUrl;

    private String roles = "";

    private String permissions = "";

    private boolean active = true;

    /*
           USER --- follows --- USER     ManyToMany
    */
    @ManyToMany(cascade =
            {
                CascadeType.ALL
            },
            fetch=FetchType.EAGER
    )
    @JoinTable(
            name="user_follows_user",
            joinColumns = @JoinColumn(name="user_id_following"),
            inverseJoinColumns = @JoinColumn(name = "user_id_followed")
    )
    private Set<User> follows = new HashSet<>();



    /*
            USER --- followed by --- USER   ManyToMany
         */
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "follows",
            fetch=FetchType.EAGER
    )
    private Set<User> followedBy = new HashSet<>();


    /*
            USER --- posts --- POST     OneToMany
         */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Post> posts;

    /*
        USER --- likes --- POST     ManyToMany
     */
    @ManyToMany(
            fetch = FetchType.EAGER
    )
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

    public User(String username, String password, String roles, String permissions){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }

    public User(String username, String password, String email, String firstName, String lastName, long birthdate, String pictureUrl, String roles, String permissions) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.pictureUrl = pictureUrl;
        this.roles = roles;
        this.permissions = permissions;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // FUNCTIONS
    public void incrementNumberOfPosts(){
        numberOfPosts++;
    }

    public void decrementNumberOfPosts(){
        numberOfPosts--;
    }

    public boolean addNewFollowing(User newFollowing){
        return follows.add(newFollowing);
    }

    public boolean removeFollowing(User following){
        return follows.remove(following);
    }

    public boolean addNewFollowedBy(User newFollowedBy){
        return followedBy.add(newFollowedBy);
    }

    public boolean removeFollowedBy(User followedBy){
        return this.followedBy.remove(followedBy);
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

    public Set<User> getFollows() {
        return follows;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

    public boolean addRole(String role){
        if(roles.contains(role))
            return false;
        if(roles.length() > 0){
            roles += "," + role;
        }else{
            roles += role;
        }
        return true;
    }

    public boolean removeRole(String role){
        String[] r = roles.split(",");
        roles = "";

        for(int i = 0; i < r.length; ++i){
            if(!r[i].equals(role))
                roles += role + ",";
        }
        roles = roles.substring(roles.length()-1);

        return true;
    }
}
