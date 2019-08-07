package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.Authority;

import javax.persistence.*;
import java.util.Date;

/**
 * User ID - unique primary key
 * First Name - String firstName (only letters)
 * Last Name -
 * Password
 * Authority
 */
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
    @Column(name="birthdate")
    private Date birthdate;

    public User() {
    }

    public User(String firstName, String lastName, String password, Authority authority,
                String email, String pictureUrl, int numberOfPosts, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authority = authority;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.numberOfPosts = numberOfPosts;
        this.birthdate = birthdate;
    }

    void changeLevelOfAuthority(Authority authority) {
        this.authority = authority;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return firstName;
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
