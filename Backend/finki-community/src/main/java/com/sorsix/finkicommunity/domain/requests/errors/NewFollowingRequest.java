package com.sorsix.finkicommunity.domain.requests.errors;

public class NewFollowingRequest {
    private Long userIdFollowing;
    private Long userIdFollowed;

    public NewFollowingRequest() {
    }

    public NewFollowingRequest(Long userIdFollowing, Long userIdFollowed) {
        this.userIdFollowing = userIdFollowing;
        this.userIdFollowed = userIdFollowed;
    }

    public Long getUserIdFollowing() {
        return userIdFollowing;
    }

    public void setUserIdFollowing(Long userIdFollowing) {
        this.userIdFollowing = userIdFollowing;
    }

    public Long getUserIdFollowed() {
        return userIdFollowed;
    }

    public void setUserIdFollowed(Long userIdFollowed) {
        this.userIdFollowed = userIdFollowed;
    }
}
