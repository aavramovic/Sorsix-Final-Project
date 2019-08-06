package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "name_of_course")
    private String nameOfCourse;
    @Column(name = "course_description")
    private String courseDescription;

    public Course() {
    }

    public Course(long courseId, String nameOfCourse, String courseDescription) {
        this.courseId = courseId;
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
