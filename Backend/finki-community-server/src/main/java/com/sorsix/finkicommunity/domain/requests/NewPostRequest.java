package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewPostRequest {
    @NotNull @NotEmpty
    public String title;
    @NotNull @NotEmpty
    public String content;
    @NotNull @NotEmpty
    public String username;

    public String courseName = null;
    public Long replyToPostId = null;
}
