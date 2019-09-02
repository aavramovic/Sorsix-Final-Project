package com.sorsix.finkicommunity.domain.responses.user;

import com.sorsix.finkicommunity.domain.enums.Role;

public class UserResponse {
    public long expiresIn;
    public String idToken;
    public Role role;
    public String errorMessage = "Incorrect password";
    public boolean valid = false;
    public String username;
}
