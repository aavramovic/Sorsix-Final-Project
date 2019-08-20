package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, QueryByExampleExecutor<Course>{
    boolean existsCourseByCourseName(String courseName);
    List<Course> findCoursesByProgramsContaining(String program);
    List<Course> findCoursesByProgramsContainingAndStudyYear(String program, StudyYear studyYear);
    List<Course> findCoursesByProgramsContainingAndStudyYearAndSemester(String program, StudyYear studyYear, Semester semester);
    List<Course> findCoursesByProgramsContainingAndStudyYearAndSemesterAndCourseType(String program, StudyYear studyYear, Semester semester, CourseType courseType);
    Course findCourseByCourseName(String courseName);
    List<Course> findCoursesByProgramsContainingAndStudyYearContainingAndSemesterContainingAndCourseTypeContaining(String program, String studyYear, String semester, String courseType);
    // List<Course> findCoursesByStudyYearContaining(String studyYear);
}
