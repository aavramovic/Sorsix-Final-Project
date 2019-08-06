package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "name_of_course")
    private String nameOfCourse;
    @Column(name = "course_description")
    private String courseDescription;

    public Course() {

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
