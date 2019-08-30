package com.sorsix.finkicommunity.domain.requests;

import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewCourseRequest{
    @NotNull @NotEmpty
    public String code;
    @NotNull @NotEmpty
    public String courseName;
    @NotNull @NotEmpty
    public String courseDescription;
    @NotNull @NotEmpty
    public String programs;

    public StudyYear studyYear;

    public Semester semester;

    public CourseType courseType;
}
