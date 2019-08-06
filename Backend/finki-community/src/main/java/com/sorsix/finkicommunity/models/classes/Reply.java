package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
@Table(name="replies")
public class Reply {
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="post_id_replied")
    private long postIdReplied;
    @Column(name="post_id_replier")
    private long postIdReplier;

    public Reply() {
    }

    public Reply(long postIdReplied, long postIdReplier) {
        this.postIdReplied = postIdReplied;
        this.postIdReplier = postIdReplier;
    }

    public long getPostIdReplied() {
        return postIdReplied;
    }

    public long getPostIdReplier() {
        return postIdReplier;
    }
}
