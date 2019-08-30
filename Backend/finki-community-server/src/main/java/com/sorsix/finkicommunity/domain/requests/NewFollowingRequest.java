package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotNull;

public class NewFollowingRequest {
    @NotNull
    public Long userIdFollowing;
    @NotNull
    public Long userIdFollowed;
}
