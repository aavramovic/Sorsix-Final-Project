package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewPostRequest {
    @NotNull @NotEmpty
    public String content;
    @NotNull
    public Long courseId;
    @NotNull
    public Long userIdOwner;

}
