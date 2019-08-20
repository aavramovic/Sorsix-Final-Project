import {IPostCourse} from '../Interfaces/IPostCourse';
import {Program} from '../Enumeration/Program';
import {Semester} from '../Enumeration/Semester';
import {YearOfStudy} from '../Enumeration/YearOfStudy';
import {Type} from '../Enumeration/Type';

export class PostCourse implements IPostCourse{
    courseDescription: string;
    courseName: string;
    courseType: string;
    programs: string;
    semester: string;
    studyYear: string;

    constructor(courseDescription: string, courseName: string, courseType: Type, programs: Program[], semester: Semester, studyYear: YearOfStudy) {
        this.courseDescription = courseDescription;
        this.courseName = courseName;
        this.courseType = courseType;
        this.programs = programs.toString();
        this.semester = semester;
        this.studyYear = studyYear;
    }
}
