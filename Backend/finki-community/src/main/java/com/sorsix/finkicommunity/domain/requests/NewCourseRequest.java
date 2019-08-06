package com.sorsix.finkicommunity.domain.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewCourseRequest {
    @NotEmpty
    @NotNull
    private String nameOfCourse;
    @NotEmpty
    @NotNull
    private String courseDescription;


    public NewCourseRequest() {
    }
}
