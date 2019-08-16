package com.sorsix.finkicommunity.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
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

    private int numberOfFollowers = 0;

    private int numberOfFollowings = 0;

    /*
           USER --- follows --- USER     ManyToMany
    */
    @ManyToMany(
            cascade = CascadeType.ALL,
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
            fetch = FetchType.EAGER
    )
    private Set<Post> posts = new HashSet<>();

    /*
        USER --- likes --- POST     ManyToMany
     */
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name="user_likes_post",
            joinColumns = @JoinColumn(name = "fk_user_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_post_id")
    )
    private Set<Post> postsLiked = new HashSet<>();


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

    public int getNumberOfFollowings() {
        return numberOfFollowings;
    }

    public void setNumberOfFollowings(int numberOfFollowings) {
        this.numberOfFollowings = numberOfFollowings;
    }

    // FUNCTIONS
    public void incrementNumberOfPosts(){
        numberOfPosts++;
    }

    public void decrementNumberOfPosts(){
        numberOfPosts--;
    }

    public void incrementNumberOfFollowings(){numberOfFollowings++;}

    public void decrementNumberOfFollowings(){numberOfFollowings--;}

    public void incrementNumberOfFollowers(){numberOfFollowers++;}

    public void decrementNumberOfFollowers(){numberOfFollowers--;}

    public boolean addNewFollowing(User newFollowing){
        this.incrementNumberOfFollowings();
        newFollowing.incrementNumberOfFollowers();
        return follows.add(newFollowing);
    }

    public boolean removeFollowing(User following){
        this.decrementNumberOfFollowings();
        following.decrementNumberOfFollowers();
        return follows.remove(following);
    }

    public boolean addNewFollowedBy(User newFollowedBy){
        this.incrementNumberOfFollowers();
        newFollowedBy.incrementNumberOfFollowings();
        return followedBy.add(newFollowedBy);
    }

    public boolean removeFollowedBy(User followedBy){
        this.decrementNumberOfFollowers();
        followedBy.decrementNumberOfFollowings();
        return this.followedBy.remove(followedBy);
    }

    public boolean addNewPost(Post newPost){
        this.incrementNumberOfPosts();
        return posts.add(newPost);
    }

    public boolean removePost(Post post){
        this.decrementNumberOfPosts();
        return posts.remove(post);
    }

    public boolean addPostLiked(Post newPostLiked){
        newPostLiked.incrementNumberOfLikes();
        return postsLiked.add(newPostLiked);
    }

    public boolean removePostLiked(Post postLiked){
        postLiked.decrementNumberOfLikes();
        return postsLiked.remove(postLiked);
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    @JsonIgnore
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

    public boolean removeRole(String roleParam){
        String[] r = roles.split(",");
        StringBuilder stringBuilder = new StringBuilder();

        for(String role: r){
            if(!role.equals(roleParam))
                stringBuilder.append(role + ",");
        }
        roles = stringBuilder.substring(0, stringBuilder.length()-1).toString();

        return true;
    }
}
