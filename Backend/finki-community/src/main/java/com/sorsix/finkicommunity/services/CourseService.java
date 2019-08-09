package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id){
        return courseRepository.findCourseByCourseId(id);
    }

    public Course createNewCourse(Course newCourse){
        return courseRepository.save(newCourse);
    }

    public Course getTopCourse(){
        return courseRepository.findTopByOrderByCourseId();
    }

    public List<Course> getTop10Courses(){
        return courseRepository.findTop10ByOrderByCourseId();
    }
}
