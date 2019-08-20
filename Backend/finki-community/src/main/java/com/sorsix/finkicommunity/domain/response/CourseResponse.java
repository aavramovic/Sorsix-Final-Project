package com.sorsix.finkicommunity.domain.response;

public class CourseResponse {
    private long courseId;
    private String code;
    private String courseName;
    private String courseDescription;

//    private String program;
//    private String studyYear;
//    private String semester;
//    private String courseType;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

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

//    public String getProgram() {
//        return program;
//    }
//
//    public void setProgram(String program) {
//        this.program = program;
//    }
//
//    public String getStudyYear() {
//        return studyYear;
//    }
//
//    public void setStudyYear(String studyYear) {
//        this.studyYear = studyYear;
//    }
//
//    public String getSemester() {
//        return semester;
//    }

//    public void setSemester(String semester) {
//        this.semester = semester;
//    }
//
//    public String getCourseType() {
//        return courseType;
//    }
//
//    public void setCourseType(String courseType) {
//        this.courseType = courseType;
//    }
}
