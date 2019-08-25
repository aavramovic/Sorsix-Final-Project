package com.sorsix.finkicommunity.domain.requests;

import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewCourseRequest {

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @NotEmpty
    private String courseName;

    @NotNull
    @NotEmpty
    private String courseDescription;

    @NotNull
    @NotEmpty
    private String programs;

    private StudyYear studyYear;

    private Semester semester;

    private CourseType courseType;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public String getStudyYear() {
        return studyYear.toString();
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public String getSemester() {
        return semester.toString();
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getCourseType() {
        return courseType.toString();
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
