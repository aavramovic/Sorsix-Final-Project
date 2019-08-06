package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.models.classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseByCourseId(long courseId);
}
