package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.models.classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseById(long id);
    Course getCourseByCourseId(long courseId);
}