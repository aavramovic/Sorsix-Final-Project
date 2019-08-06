package com.sorsix.finkicommunity.models.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

    @Column(name="course_id")
    private long id;

    @Column(name="name_of_course")
    private String nameOfCourse;

    @Column(name="course_description")
    private String courseDescription;

    public Course(){

    }

    public Course(long id, String nameOfCourse, String courseDescription) {
        this.id = id;
        this.nameOfCourse = nameOfCourse;
        this.courseDescription = courseDescription;
    }

    public long getId() {
        return id;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
