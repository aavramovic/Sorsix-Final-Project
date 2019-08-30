package com.sorsix.finkicommunity.domain.requests;

import com.sorsix.finkicommunity.domain.enums.Role;
import javax.validation.constraints.NotNull;

public class RoleChangeRequest {
    @NotNull
    public String username;
    @NotNull
    public Role role;
}
