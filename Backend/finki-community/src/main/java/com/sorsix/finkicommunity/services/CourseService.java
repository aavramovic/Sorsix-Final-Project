package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.domain.response.CourseResponse;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.domain.response.ClickedCourseResponse;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Course createNewCourse(NewCourseRequest newCourseRequest){
        Course course = new Course();

        // String courseName = newCourseRequest.getCourseName();
        course.setCourseName(newCourseRequest.getCourseName());
        course.setCourseName(newCourseRequest.getCourseName());
        course.setCourseDescription(newCourseRequest.getCourseDescription());
        course.setSemester(newCourseRequest.getSemester());
        course.setStudyYear(newCourseRequest.getStudyYear());
        course.setPrograms(newCourseRequest.getProgram());

        try{
            return courseRepository.save(course);
        }
        catch(Exception e){     // ConstraintViolationException
            return null;
        }
    }

    public Optional<List<CourseResponse>> getCoursesByProgramStudyYearSemesterCourseType(String _program, String _studyYear, String _semester, String _type){
        String program = null;
        String studyYear = null;
        String semester = null;
        String type = null;

        try {
            if (_program != null) {
                program = Program.valueOf(_program.toUpperCase()).toString();
            } else {
                program = "";
            }
            if (_studyYear != null) {
                studyYear = StudyYear.valueOf(_studyYear.toUpperCase()).toString();
            } else {
                studyYear = "";
            }
            if (_semester != null) {
                semester = Semester.valueOf(_semester.toUpperCase()).toString();
            } else {
                semester = "";
            }
            if (_type != null) {
                type = CourseType.valueOf(_type.toUpperCase()).toString();
            } else {
                type = "";
            }
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }


        List<Course> courses = courseRepository.findCoursesByProgramsContainingAndStudyYearContainingAndSemesterContainingAndCourseTypeContaining(
                program,studyYear,semester,type
        );


        List<CourseResponse> courseResponses = new ArrayList<>();
        CourseResponse courseResponse;
        for(Course course : courses){
            courseResponse = new CourseResponse();

            courseResponse.setCourseId(course.getCourseId());
            courseResponse.setCode(course.getCode());
            courseResponse.setCourseDescription(course.getCourseDescription());
            courseResponse.setCourseName(course.getCourseName());
//            courseResponse.setProgram(course.getPrograms());
//            courseResponse.setStudyYear(course.getStudyYear());
//            courseResponse.setSemester(course.getSemester());
//            courseResponse.setCourseType(course.getCourseType());

            courseResponses.add(courseResponse);
        }

        return Optional.of(courseResponses);
//        try{
//            if(_program != null){
//                program = Program.valueOf(_program.toUpperCase());
//                if(_studyYear != null){
//                    studyYear = StudyYear.valueOf(_studyYear.toUpperCase());
//
//                    if(_semester != null){
//                        semester = Semester.valueOf(_semester.toUpperCase());
//
//                        if(_type != null){
//                            type = CourseType.valueOf(_type.toUpperCase());
//                            return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYearAndSemesterAndCourseType(program.toString(), studyYear, semester, type));
//                        }
//                        return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYearAndSemester(program.toString(), studyYear, semester));
//                    }
//                    return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYear(program.toString(), studyYear));
//                }
//                return Optional.of(courseRepository.findCoursesByProgramsContaining(program.toString()));
//            }
//            return Optional.of(courseRepository.findAll());
//
//        }catch(IllegalArgumentException ex){
//            return Optional.empty();
//        }
    }

    public Optional<ClickedCourseResponse> getPostsOfCourseByCourseName(String courseName, Long noOfPosts){
        Course course = courseRepository.findCourseByCourseName(courseName);
        if(course != null){

            ClickedCourseResponse clickedCourseResponse = new ClickedCourseResponse();

            clickedCourseResponse.setCode(course.getCode());
            clickedCourseResponse.setCourseName(course.getCourseName());
            clickedCourseResponse.setCourseDescription(course.getCourseDescription());
            clickedCourseResponse.setStudyYear(course.getStudyYear().toString());
            clickedCourseResponse.setSemester(course.getSemester().toString());
            clickedCourseResponse.setPrograms(course.getPrograms());
            clickedCourseResponse.setCourseType(course.getCourseType().toString());
            clickedCourseResponse.setNumberOfPosts(course.getNumberOfPosts());
            clickedCourseResponse.setNumberOfReplies(course.getNumberOfReplies());

            if(noOfPosts != null){
                clickedCourseResponse.setPosts(course.getPosts().stream().limit(noOfPosts).collect(Collectors.toSet()));
            }else{
                clickedCourseResponse.setPosts(course.getPosts().stream().limit(10).collect(Collectors.toSet()));
            }
            return Optional.of(clickedCourseResponse);

        }else{
            return Optional.empty();
        }
    }
}
