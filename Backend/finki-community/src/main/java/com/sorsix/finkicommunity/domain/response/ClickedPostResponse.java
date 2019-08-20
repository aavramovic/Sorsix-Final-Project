package com.sorsix.finkicommunity.domain.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.sorsix.finkicommunity.domain.entities.Post;

import java.util.HashSet;
import java.util.Set;

public class ClickedPostResponse {
    PostResponse postResponse;
    Set<Post> replies = new HashSet<>();

    @JsonGetter(value = "post")
    public PostResponse getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(PostResponse postResponse) {
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
