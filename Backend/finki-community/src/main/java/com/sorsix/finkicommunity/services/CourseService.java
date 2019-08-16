package com.sorsix.finkicommunity.services;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import com.sorsix.finkicommunity.domain.requests.NewCourseRequest;
import com.sorsix.finkicommunity.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        course.setProgram(newCourseRequest.getProgram());

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
                            return Optional.of(courseRepository.findCoursesByProgramAndStudyYearAndSemesterAndCourseType(program, studyYear, semester, type));
                        }
                        return Optional.of(courseRepository.findCoursesByProgramAndStudyYearAndSemester(program, studyYear, semester));
                    }
                    return Optional.of(courseRepository.findCoursesByProgramAndStudyYear(program, studyYear));
                }
                return Optional.of(courseRepository.findCoursesByProgram(program));
            }
            return Optional.of(courseRepository.findAll());

        }catch(IllegalArgumentException ex){
            return Optional.empty();
        }
    }
}
