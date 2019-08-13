package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsCourseByCourseName(String courseName);
    List<Course> findCoursesByProgram(Program program);
    List<Course> findCoursesByProgramAndStudyYear(Program program, StudyYear studyYear);
    List<Course> findCoursesByProgramAndStudyYearAndSemester(Program program, StudyYear studyYear, Semester semester);
}
