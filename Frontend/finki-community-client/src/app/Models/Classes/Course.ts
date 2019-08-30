import {Program} from '../Enumeration/Program';
import {Type} from '../Enumeration/Type';
import {Semester} from '../Enumeration/Semester';

export class Course {
    courseName: string;
    courseId: number;
    code: string;
    description: string;
    yearOfStudy: number;
    program: Program[];
    type: Type;
    semester: Semester;

    //test constructor
    constructor(name: string, courseId: number, code: string, description: string, yearOfStudy: number, program: Program[], semester: Semester, type: Type) {
        this.courseName = name;
        this.courseId = courseId;
        this.code = code;
        this.description = description;
        this.yearOfStudy = yearOfStudy;
        this.program = program;
        this.semester = semester;
        this.type = type;
    }

}
