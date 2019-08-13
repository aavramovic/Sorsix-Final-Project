package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private long courseId;

    @Column(name = "course_name", unique = true)
    private String courseName;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "study_year")
    private StudyYear studyYear;

    @Column(name = "semester")
    private Semester semester;

    @Column(name = "program")
    private Program program;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Post> posts;

    /*
        Needed for JPA auto table creation
     */
    public Course(){
    }

    public Course(String courseName, String courseDescription, StudyYear studyYear, Semester semester, Program program) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.studyYear = studyYear;
        this.semester = semester;
        this.program = program;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescriptionLink) {
        this.courseDescription = courseDescriptionLink;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
