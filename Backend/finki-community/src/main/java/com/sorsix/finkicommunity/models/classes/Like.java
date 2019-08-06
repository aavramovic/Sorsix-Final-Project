package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "post_id")
    private long postId;
    @Column(name = "user_id")
    private long userId;
}
