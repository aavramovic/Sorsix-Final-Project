package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.requests.errors.MalFormedNewCourseRequest;
import com.sorsix.finkicommunity.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/forum")

// Enabling Cross Origin Requests for a RESTful Web Service
@CrossOrigin(origins = "http://localhost:4200")
public class ForumController {
    private CourseService courseService;

    public ForumController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to the forum FINKI-COMMUNITY");
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/courses/new")
    public ResponseEntity createNewCourse(@RequestBody @Valid Course newCourse, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(
                            new MalFormedNewCourseRequest()
                    );
        }else{
            return ResponseEntity.ok(courseService.createNewCourse(newCourse));
        }

    }

}
