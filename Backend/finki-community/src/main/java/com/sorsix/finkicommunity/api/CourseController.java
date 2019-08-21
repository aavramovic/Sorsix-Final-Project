package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.response.course.SimpleCourseResponse;
import com.sorsix.finkicommunity.services.CourseService;
import org.springframework.http.HttpStatus;
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

    /*
    GET METHODS
     */
    @GetMapping("/filter")
    ResponseEntity<List<SimpleCourseResponse>> getCoursesByProgramStudyYearSemesterCourseType(
            @RequestParam(required = false) String program,
            @RequestParam(required = false) String studyYear,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) String type
    ){
        return courseService.getCoursesByProgramStudyYearSemesterCourseType(program, studyYear, semester, type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/clicked")
    public ResponseEntity getCourseByCourseName(
            @RequestParam String courseName,
            @RequestParam(required = false) Long noOfPosts
    ){
        return courseService.getPostsOfCourseByCourseName(courseName, noOfPosts)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    /*
    POST METHODS
     */
    @PostMapping("/new")
    public ResponseEntity createNewCourse(@RequestBody @Valid NewCourseRequest newCourseRequest){
        return courseService.createNewCourse(newCourseRequest)
                .map(courseRequest -> ResponseEntity.status(HttpStatus.CREATED).body(courseRequest))
                .orElseGet(()-> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
