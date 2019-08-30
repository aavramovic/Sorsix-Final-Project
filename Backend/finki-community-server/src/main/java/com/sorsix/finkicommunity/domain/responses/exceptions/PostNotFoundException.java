package com.sorsix.finkicommunity.domain.responses.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String message){
        super(message);
    }
}
