package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.responses.post.SimplePostResponse;
import com.sorsix.finkicommunity.domain.responses.course.SimpleCourseResponse;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.domain.responses.course.ClickedCourseResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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

        List<Course> courses = courseRepository.
                findCoursesByProgramsContainingAndStudyYearContainingAndSemesterContainingAndCourseTypeContaining(program, studyYear, semester, type);

        List<SimpleCourseResponse> courseResponses = convertCourseToSimpleCourseResponse(courses);

        return Optional.of(courseResponses);
    }

    public Optional<ClickedCourseResponse> getPostsOfCourseByCourseName(String courseName, Long noOfPosts) {
        courseRepository.findCourseByCourseName(courseName);

        return courseRepository
                .findCourseByCourseName(courseName)
                .map(
                        course -> {
                            ClickedCourseResponse clickedCourseResponse = new ClickedCourseResponse();

                            clickedCourseResponse.code = course.getCode();
                            clickedCourseResponse.courseName = course.getCourseName();
                            clickedCourseResponse.courseDescription = course.getCourseDescription();
                            clickedCourseResponse.studyYear = course.getStudyYear();
                            clickedCourseResponse.semester = course.getSemester();
                            clickedCourseResponse.programs = course.getPrograms();
                            clickedCourseResponse.courseType = course.getCourseType();
                            clickedCourseResponse.numberOfPosts = course.getNumberOfPosts();
                            clickedCourseResponse.numberOfReplies = course.getNumberOfReplies();
                            if (noOfPosts != null) {
                                clickedCourseResponse.posts = convertFromPostToSimplePostResponse(course.getPosts().stream().limit(noOfPosts).collect(Collectors.toList()));
                            } else {
                                // DEFAULT 10
                                clickedCourseResponse.posts = convertFromPostToSimplePostResponse(course.getPosts().stream().limit(10).collect(Collectors.toList()));
                            }
                            return Optional.of(clickedCourseResponse);
                        })
                .orElseGet(() -> Optional.empty());
    }

    public Optional<NewCourseRequest> createNewCourse(NewCourseRequest newCourseRequest) {

        Course course = new Course();

        course.setCode(newCourseRequest.code);
        course.setCourseName(newCourseRequest.courseName);
        course.setCourseDescription(newCourseRequest.courseDescription);
        course.setSemester(newCourseRequest.semester.toString());
        course.setStudyYear(newCourseRequest.studyYear.toString());
        course.setPrograms(newCourseRequest.programs);
        course.setCourseType(newCourseRequest.courseType.toString());

        try {
            courseRepository.save(course);              // ConstraintViolationException
            return Optional.of(newCourseRequest);
        } catch (RuntimeException e) {
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

            courseResponse.courseId = course.getCourseId();
            courseResponse.code = course.getCode();
            courseResponse.courseDescription = course.getCourseDescription();
            courseResponse.courseName = course.getCourseName();

            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    private Set<SimplePostResponse> convertFromPostToSimplePostResponse(List<Post> posts) {
        Set<SimplePostResponse> postResponses = new TreeSet<>();

        for (Post post : posts) {
            postResponses.add(createSimplePostResponseFromPost(post));
        }

        return postResponses;
    }

    private SimplePostResponse createSimplePostResponseFromPost(Post post) {
        SimplePostResponse postResponse = new SimplePostResponse();

        postResponse.id = post.getPostId();
        postResponse.content = post.getContent();
        postResponse.courseName = post.getCourse().getCourseName();
        postResponse.noOfComments = post.getNumberOfReplies();
        postResponse.noOfLikes = post.getNumberOfLikes();
        postResponse.timeOfPost = post.getTimestamp();
        postResponse.title = post.getTitle();
        postResponse.username = post.getUser().getUsername();

        return postResponse;
    }
}
