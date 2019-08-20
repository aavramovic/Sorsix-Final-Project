package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.repository.CourseRepository;
import com.sorsix.finkicommunity.response.CourseResponse;
import org.springframework.stereotype.Service;
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

    public Optional<List<Course>> getCoursesByProgramStudyYearSemester(String _program, String _studyYear, String _semester, String _type){
        Program program;
        StudyYear studyYear;
        Semester semester;
        CourseType type;



        try{
            if(_program != null){
                program = Program.valueOf(_program.toUpperCase());
                if(_studyYear != null){
                    studyYear = StudyYear.valueOf(_studyYear.toUpperCase());

                    if(_semester != null){
                        semester = Semester.valueOf(_semester.toUpperCase());

                        if(_type != null){
                            type = CourseType.valueOf(_type.toUpperCase());
                            return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYearAndSemesterAndCourseType(program.toString(), studyYear, semester, type));
                        }
                        return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYearAndSemester(program.toString(), studyYear, semester));
                    }
                    return Optional.of(courseRepository.findCoursesByProgramsContainingAndStudyYear(program.toString(), studyYear));
                }
                return Optional.of(courseRepository.findCoursesByProgramsContaining(program.toString()));
            }
            return Optional.of(courseRepository.findAll());

        }catch(IllegalArgumentException ex){
            return Optional.empty();
        }
    }

    public Optional<CourseResponse> getPostsOfCourseByCourseName(String courseName, Long noOfPosts){
        Course course = courseRepository.findCourseByCourseName(courseName);
        if(course != null){

            CourseResponse courseResponse = new CourseResponse();

            courseResponse.setCode(course.getCode());
            courseResponse.setCourseName(course.getCourseName());
            courseResponse.setCourseDescription(course.getCourseDescription());
            courseResponse.setStudyYear(course.getStudyYear().toString());
            courseResponse.setSemester(course.getSemester().toString());
            courseResponse.setPrograms(course.getPrograms());
            courseResponse.setCourseType(course.getCourseType().toString());
            courseResponse.setNumberOfPosts(course.getNumberOfPosts());
            courseResponse.setNumberOfReplies(course.getNumberOfReplies());

            if(noOfPosts != null){
                courseResponse.setPosts(course.getPosts().stream().limit(noOfPosts).collect(Collectors.toSet()));
            }else{
                courseResponse.setPosts(course.getPosts().stream().limit(10).collect(Collectors.toSet()));
            }
            return Optional.of(courseResponse);

        }else{
            return Optional.empty();
        }
    }
}
