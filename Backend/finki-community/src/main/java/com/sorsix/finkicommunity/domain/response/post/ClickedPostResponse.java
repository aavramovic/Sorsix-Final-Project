package com.sorsix.finkicommunity.domain.response.post;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.sorsix.finkicommunity.domain.entities.Post;

import java.util.HashSet;
import java.util.Set;

public class ClickedPostResponse {
    SimplePostResponse postResponse;
    Set<Post> replies = new HashSet<>();

    @JsonGetter(value = "post")
    public SimplePostResponse getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(SimplePostResponse postResponse) {
        this.postResponse = postResponse;
    }

    @JsonGetter(value = "replies")
    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }
}
