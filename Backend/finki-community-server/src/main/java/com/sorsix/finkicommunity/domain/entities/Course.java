package com.sorsix.finkicommunity.domain.entities;

import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;


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

    /*
    FOUR FOLLOWING PROPERTIES ARE STORED AS STRINGS INSTEAD OF enum TYPES FOR JPA QUERY PURPOSE
     */
    @Column(name = "study_year")
    private String studyYear;

    @Column(name = "semester")
    private String semester;

    @Column(name = "programs")
    private String programs;

    @Column(name = "course_type")
    private String courseType;


    @Column(name = "number_of_posts")
    private int numberOfPosts = 0;

    @Column(name = "number_of_replies")
    private int numberOfReplies = 0;

    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.EAGER
    )
    private Set<Post> posts = new TreeSet<>();

    /*
        Needed for JPA auto table creation
     */
    public Course(){
    }

    public Course(String code, String courseName, String courseDescription, String programs, StudyYear studyYear, Semester semester, CourseType courseType) {
        this.code = code;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.programs = programs;
        this.studyYear = studyYear.toString();
        this.semester = semester.toString();
        this.courseType = courseType.toString();
    }

    // GETTERs, SETTERs
    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String progs){
        programs = progs;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(int numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    // HELPER FUNCTIONS
    public void incrementNumberOfPosts(){
        numberOfPosts++;
    }

    public void decrementNumberOfPosts(){
        numberOfPosts--;
    }

    public void incrementNumberOfReplies(){
        numberOfReplies++;
    }

    public void decrementNumberOfReplies(){
        numberOfReplies--;
    }
}
