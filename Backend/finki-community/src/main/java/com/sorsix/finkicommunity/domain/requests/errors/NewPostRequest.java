package com.sorsix.finkicommunity.domain.requests.errors;

import javax.validation.constraints.NotNull;

public class NewPostRequest {

    private String content;

    @NotNull
    private Long courseId;

    private Long postIdRepliedTo;

    @NotNull
    private Long userIdOwner;

    public String getContent() {
        return content;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getPostIdRepliedTo() {
        return postIdRepliedTo;
    }

    public Long getUserIdOwner() {
        return userIdOwner;
    }
}
