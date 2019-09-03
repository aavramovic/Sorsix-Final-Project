package com.sorsix.finkicommunity.domain.responses.post;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.util.Set;
import java.util.TreeSet;

public class ClickedPostResponse {
    private SimplePostResponse postResponse;
    private Set<SimplePostResponse> replies = new TreeSet<>();

    @JsonGetter(value = "post")
    public SimplePostResponse getPostResponse() {
        return postResponse;
    }

    public void setPostResponse(SimplePostResponse postResponse) {
        this.postResponse = postResponse;
    }

    @JsonGetter(value = "replies")
    public Set<SimplePostResponse> getReplies() {
        return replies;
    }

    public void setReplies(Set<SimplePostResponse> replies) {
        this.replies = replies;
    }
}
