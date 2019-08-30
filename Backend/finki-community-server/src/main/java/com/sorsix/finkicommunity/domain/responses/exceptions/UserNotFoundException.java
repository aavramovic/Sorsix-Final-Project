package com.sorsix.finkicommunity.domain.responses.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message){
        super(message);
    }
}
