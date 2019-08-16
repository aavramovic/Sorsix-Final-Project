package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.CourseType;
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

    @Column(unique = true, nullable = false)
    private String code;

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

    @Column(name = "course_type")
    private CourseType courseType;

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

    public Course(String courseName, String courseDescription, Program program,
                  StudyYear studyYear, Semester semester, CourseType courseType) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.studyYear = studyYear;
        this.semester = semester;
        this.program = program;
        this.courseType = courseType;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
