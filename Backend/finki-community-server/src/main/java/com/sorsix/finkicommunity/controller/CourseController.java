package com.sorsix.finkicommunity.controller;

import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.responses.course.ClickedCourseResponse;
import com.sorsix.finkicommunity.domain.responses.course.SimpleCourseResponse;
import com.sorsix.finkicommunity.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/forum/courses")
@CrossOrigin  // Enabling Cross Origin Requests for a RESTful Web Service
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
    public ResponseEntity<ClickedCourseResponse> getCourseByCourseName(
            @RequestParam String courseName,
            @RequestParam(required = false) Long noOfPosts,
            @RequestParam(required = false) String username
    ){
        return courseService.getPostsOfCourseByCourseName(courseName, noOfPosts, username)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/coursenames")
    public ResponseEntity<List<String>> getAllCourseNames(){
        return ResponseEntity.ok(courseService.getAllCourseNames());
    }

    /*
    POST METHODS
     */
    @PostMapping("/new")
    public ResponseEntity<NewCourseRequest> createNewCourse(@RequestBody @Valid NewCourseRequest newCourseRequest){
        return courseService.createNewCourse(newCourseRequest)
                .map(courseRequest -> ResponseEntity.status(HttpStatus.CREATED).body(courseRequest))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
}
