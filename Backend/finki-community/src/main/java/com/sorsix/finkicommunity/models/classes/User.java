package com.sorsix.finkicommunity.models.classes;

import com.sorsix.finkicommunity.models.enumerations.Authority;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private int userId;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "authority")
    private Authority authority;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "picture_url")
    private String pictureUrl;
    @Column(name = "number_of_posts")
    private int numberOfPosts;

    public User() {
    }

    public User(int userId, String name, String lastName, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.authority = Authority.REGULAR;
        this.email = email;
        this.pictureUrl = "#";
        this.numberOfPosts = 0;
    }

    void changeLevelOfAuthority(Authority authority) {
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }
}
