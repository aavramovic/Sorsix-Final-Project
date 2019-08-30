package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUserRequest {
    @NotNull @NotEmpty
    public String username;

    @NotNull @NotEmpty @Email
    public String email;

    @NotNull @NotEmpty
    public String password;

    @NotEmpty @NotNull
    public String firstName;

    @NotEmpty @NotNull
    public String lastName;

    @NotNull
    public Character sex;

    @NotNull
    public long birthdate;
}
