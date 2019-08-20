package com.sorsix.finkicommunity.api;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.response.CourseResponse;
import com.sorsix.finkicommunity.repository.CourseRepository;
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

    // GET METHODS
    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id)
    {
        return courseService.getCourseById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    ResponseEntity<List<CourseResponse>> getCoursesByProgramStudyYearSemesterCourseType(
            @RequestParam(required = false) String program,
            @RequestParam(required = false) String studyYear,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) String type
    )
    {
        //return ResponseEntity.ok(program + " " + studyYear + " " + semester + " " + type);
        return courseService.getCoursesByProgramStudyYearSemesterCourseType(program, studyYear, semester, type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

        //return ResponseEntity.ok(courseService.getCoursesByProgramStudyYearSemesterCourseType(program, studyYear, semester, type));
    }

    @GetMapping("/clicked")
    public ResponseEntity getCourseByCourseName(
            @RequestParam String courseName,
            @RequestParam(required = false) Long noOfPosts
    )
    {
            return courseService.getPostsOfCourseByCourseName(courseName, noOfPosts)
                    .map(ResponseEntity::ok)
                    .orElseGet(()-> ResponseEntity.notFound().build());
    }

    // POST METHODS
    @PostMapping("/new")
    public ResponseEntity createNewCourse(@RequestBody @Valid NewCourseRequest newCourseRequest){
        return ResponseEntity.ok(courseService.createNewCourse(newCourseRequest));
    }
}
