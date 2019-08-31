import {IPostCourse} from '../Interfaces/IPostCourse';
import {Program} from '../Enumeration/Program';
import {Semester} from '../Enumeration/Semester';
import {YearOfStudy} from '../Enumeration/YearOfStudy';
import {Type} from '../Enumeration/Type';

export class PostCourse implements IPostCourse {
    courseDescription: string;
    courseName: string;
    courseType: string;
    programs: string;
    semester: string;
    studyYear: string;
    code: string;

    constructor(courseDescription: string,
                courseName: string,
                courseType: Type | string,
                programs: Program[] | string,
                semester: Semester | string,
                studyYear: YearOfStudy | string,
                code: string) {
        this.courseDescription = courseDescription;
        this.courseName = courseName;
        this.courseType = courseType;
        this.programs = programs.toString();
        this.semester = semester;
        this.studyYear = studyYear;
        this.code = code;
    }
}
