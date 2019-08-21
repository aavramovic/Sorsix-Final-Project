package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, QueryByExampleExecutor<Course>{
    Course findCourseByCourseName(String courseName);
    List<Course> findCoursesByProgramsContainingAndStudyYearContainingAndSemesterContainingAndCourseTypeContaining(String program, String studyYear, String semester, String courseType);
}
