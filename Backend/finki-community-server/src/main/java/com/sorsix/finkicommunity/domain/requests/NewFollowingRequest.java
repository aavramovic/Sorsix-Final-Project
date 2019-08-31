package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewFollowingRequest {
    @NotNull @NotEmpty
    public String usernameFollowing;
    @NotNull
    public Long userIdFollowed;
}
