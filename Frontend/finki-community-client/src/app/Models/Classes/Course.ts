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
    constructor(name: string, courseId: number, code: string, description: string, yearOfStudy: number, program: Program[], type: Type) {
        this.courseName = name;
        this.courseId = courseId;
        this.code = code;
        this.description = description;
        this.yearOfStudy = yearOfStudy;
        this.program = program;
        this.type = type;
    }

//TODO:// ova treba da se napravi preku request
    //
    /*    addUserToCourse(user: User): void{
            if(!this._users.includes(user))
             this._users.push(user);
        }

        deleteUserFromCourse(user: User) {
            this._users = this._users.filter(item => item !== user);
        }*/
}
