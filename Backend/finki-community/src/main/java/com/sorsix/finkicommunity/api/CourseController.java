package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forum/courses")
@CrossOrigin(origins = "http://localhost:4200")  // Enabling Cross Origin Requests for a RESTful Web Service
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity createNewCourse(@RequestBody @Valid Course newCourse, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body("Bad Request");
        }else{
            return ResponseEntity.ok(courseService.createNewCourse(newCourse));
        }
    }

    @GetMapping("/top")
    public ResponseEntity getTop10Courses(){
        return ResponseEntity.ok(courseService.getTop10Courses());
    }
}
