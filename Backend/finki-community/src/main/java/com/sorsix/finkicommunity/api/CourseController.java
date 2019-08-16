package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.services.CourseService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/all")
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
    public ResponseEntity createNewCourse(@RequestBody @Valid NewCourseRequest newCourseRequest){
        return ResponseEntity.ok(courseService.createNewCourse(newCourseRequest));
    }

    @GetMapping
    ResponseEntity<List<Course>> getCoursesByProgramStudyYearSemester(
            @RequestParam(required = false) String program,
            @RequestParam(required = false) String studyYear,
            @RequestParam(required = false) String semester)
    {
        return courseService.getCoursesByProgramStudyYearSemester(program, studyYear, semester)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
