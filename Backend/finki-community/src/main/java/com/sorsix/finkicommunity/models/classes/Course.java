package com.sorsix.finkicommunity.models.classes;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
>>>>>>> 976ba12a2c25aae148ef6a714fd153b7cbc335be
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "name_of_course")
    private String nameOfCourse;
    @Column(name = "course_description")
    private String courseDescription;

    public Course() {
    }

<<<<<<< HEAD
    public Course(String nameOfCourse, String courseDescription) {
=======
    public Course(long courseId, String nameOfCourse, String courseDescription) {
        this.courseId = courseId;
>>>>>>> 976ba12a2c25aae148ef6a714fd153b7cbc335be
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
