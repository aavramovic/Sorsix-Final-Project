package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewPostLikeRequest {
    @NotNull @NotEmpty
    public String username;
    @NotNull
    public long postId;
}
