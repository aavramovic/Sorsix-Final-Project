package com.sorsix.finkicommunity.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "name_of_course")
    private String nameOfCourse;
    @Column(name = "course_description")
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
}
