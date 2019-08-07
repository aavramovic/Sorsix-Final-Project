package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findTopByOrderByCourseId();
    List<Course> findTop10ByOrderByCourseId();
}
