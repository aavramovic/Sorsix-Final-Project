package com.sorsix.finkicommunity.domain.requests;

import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewCourseRequest {

    @NotNull
    @NotEmpty
    private String courseName;

    @NotNull
    @NotEmpty
    private String courseDescription;

    private String programs;

    private String studyYear;

    private String semester;

    private String courseType;


    public NewCourseRequest() {
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getProgram() {
        return programs;
    }

    public String getCourseType() {
        return courseType;
    }
}
