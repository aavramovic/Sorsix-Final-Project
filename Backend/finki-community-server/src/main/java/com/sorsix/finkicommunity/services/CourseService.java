package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.response.post.SimplePostResponse;
import com.sorsix.finkicommunity.domain.response.course.SimpleCourseResponse;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.domain.response.course.ClickedCourseResponse;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<NewCourseRequest> createNewCourse(NewCourseRequest newCourseRequest) {
        Course course = new Course();

        course.setCode(newCourseRequest.getCode());
        course.setCourseName(newCourseRequest.getCourseName());
        course.setCourseName(newCourseRequest.getCourseName());
        course.setCourseDescription(newCourseRequest.getCourseDescription());
        course.setSemester(newCourseRequest.getSemester());
        course.setStudyYear(newCourseRequest.getStudyYear());
        course.setPrograms(newCourseRequest.getPrograms());
        course.setCourseType(newCourseRequest.getCourseType());

        try {
            courseRepository.save(course);              // ConstraintViolationException
            return Optional.of(newCourseRequest);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<SimpleCourseResponse>> getCoursesByProgramStudyYearSemesterCourseType(String _program, String _studyYear, String _semester, String _type) {
        String program = "";
        String studyYear = "";
        String semester = "";
        String type = "";

        try {
            if (_program != null) {
                program = Program.valueOf(_program.toUpperCase()).toString();           // throws IllegalArgumentException
            }
            if (_studyYear != null) {
                studyYear = StudyYear.valueOf(_studyYear.toUpperCase()).toString();     // throws IllegalArgumentException
            }
            if (_semester != null) {
                semester = Semester.valueOf(_semester.toUpperCase()).toString();        // throws IllegalArgumentException
            }
            if (_type != null) {
                type = CourseType.valueOf(_type.toUpperCase()).toString();              // throws IllegalArgumentException
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }

        List<Course> courses = courseRepository.findCoursesByProgramsContainingAndStudyYearContainingAndSemesterContainingAndCourseTypeContaining(
                program, studyYear, semester, type
        );

        List<SimpleCourseResponse> courseResponses = convertCourseToSimpleCourseResponse(courses);

        return Optional.of(courseResponses);
    }

    public Optional<ClickedCourseResponse> getPostsOfCourseByCourseName(String courseName, Long noOfPosts) {
        Course course = courseRepository.findCourseByCourseName(courseName);

        if (course != null) {
            ClickedCourseResponse clickedCourseResponse = new ClickedCourseResponse();

            clickedCourseResponse.setCode(course.getCode());
            clickedCourseResponse.setCourseName(course.getCourseName());
            clickedCourseResponse.setCourseDescription(course.getCourseDescription());
            clickedCourseResponse.setStudyYear(course.getStudyYear());
            clickedCourseResponse.setSemester(course.getSemester());
            clickedCourseResponse.setPrograms(course.getPrograms());
            clickedCourseResponse.setCourseType(course.getCourseType());
            clickedCourseResponse.setNumberOfPosts(course.getNumberOfPosts());
            clickedCourseResponse.setNumberOfReplies(course.getNumberOfReplies());

            if (noOfPosts != null) {
                clickedCourseResponse.setPosts(convertFromPostToPostResponse(course.getPosts().stream().limit(noOfPosts).collect(Collectors.toList())));
            } else {
                clickedCourseResponse.setPosts(convertFromPostToPostResponse(course.getPosts().stream().limit(10).collect(Collectors.toList())));
            }
            return Optional.of(clickedCourseResponse);

        } else {
            return Optional.empty();
        }
    }

    /*
     HELPER METHODS
     */
    private List<SimpleCourseResponse> convertCourseToSimpleCourseResponse(List<Course> courses) {
        List<SimpleCourseResponse> courseResponses = new ArrayList<>();
        SimpleCourseResponse courseResponse;
        for (Course course : courses) {
            courseResponse = new SimpleCourseResponse();

            courseResponse.setCourseId(course.getCourseId());
            courseResponse.setCode(course.getCode());
            courseResponse.setCourseDescription(course.getCourseDescription());
            courseResponse.setCourseName(course.getCourseName());

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    private Set<SimplePostResponse> convertFromPostToPostResponse(List<Post> posts) {
        Set<SimplePostResponse> postResponses = new HashSet<>();

        for (Post post : posts) {
            postResponses.add(createPostResponseObject(post));
        }

        return postResponses;
    }

    private SimplePostResponse createPostResponseObject(Post post) {
        SimplePostResponse postResponse = new SimplePostResponse();

        postResponse.setId(post.getPostId());
        postResponse.setContent(post.getContent());
        postResponse.setCourseName(post.getCourse().getCourseName());
        postResponse.setNoOfComments(post.getNumberOfReplies());
        postResponse.setNoOfLikes(post.getNumberOfLikes());
        postResponse.setTimeOfPost(post.getTimestamp());
        postResponse.setTitle(post.getTitle());
        postResponse.setUsername(post.getUser().getUsername());

        return postResponse;
    }
}
