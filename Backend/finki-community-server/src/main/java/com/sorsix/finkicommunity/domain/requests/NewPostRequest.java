package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewPostRequest {

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    private Long courseId;

    @NotNull
    private Long userIdOwner;

    public String getContent() {
        return content;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getUserIdOwner() {
        return userIdOwner;
    }
}
