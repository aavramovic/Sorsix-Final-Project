import {Program} from '../Enumeration/Program';

export class Course {
    courseName: string;
    _courseId: string;
    code: string;
    description: string;
    yearOfStudy: number;
    program: Program[];
    isMandatory: boolean;

    //test constructor
    constructor(name: string, courseId: string, code: string, description: string, yearOfStudy: number, program: Program[], isMandatory: boolean) {
        this.courseName = name;
        this._courseId = courseId;
        this.code = code;
        this.description = description;
        this.yearOfStudy = yearOfStudy;
        this.program = program;
        this.isMandatory = isMandatory;
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
