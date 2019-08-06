package com.sorsix.finkicommunity.models.classes;


import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="user_id")
    private long userId;
    @Column(name="number_of_likes")
    private int numberOfLikes;
    @Column(name="number_of_replies")
    private int numberOfReplies;
}
