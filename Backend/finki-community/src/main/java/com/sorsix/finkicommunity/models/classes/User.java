package com.sorsix.finkicommunity.models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String email;
    private String picture_url;
    private int number_of_posts;
    //id userId Username Password Name LastName Email Picture/Url NumberOfPosts Follows

}
