package com.sorsix.finkicommunity.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    @JsonProperty("course_id")
    private long courseId;

    @Column(name = "course_name")
    @JsonProperty("course_name")
    private String nameOfCourse;

    @Column(name = "course_description")
    @JsonProperty("course_description")
    private String courseDescription;


    /*
        Needed for JPA auto table creation
     */
    public Course() {
    }

    public Course(String nameOfCourse, String courseDescription) {
        this.nameOfCourse = nameOfCourse;
        this.courseDescription = courseDescription;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
