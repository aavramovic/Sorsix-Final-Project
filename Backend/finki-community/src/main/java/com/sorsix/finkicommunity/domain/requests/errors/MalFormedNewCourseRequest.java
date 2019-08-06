package com.sorsix.finkicommunity.domain.requests.errors;

public class MalFormedNewCourseRequest {
    private final String ERROR = "Request should be JSON format with properties ...";

    public String getERROR(){
        return ERROR;
    }
}
