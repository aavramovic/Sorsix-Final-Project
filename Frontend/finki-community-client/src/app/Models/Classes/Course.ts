export class Course {
    courseName: string;
    private courseId: string;
    code: string;
    description: string;

    //test constructor
    constructor(name: string, courseId: string, code: string, description: string) {
        this.courseName = name;
        this.courseId = courseId;
        this.code = code;
        this.description = description;
    }

//TODO ova treba da se napravi preku request
    //
/*    addUserToCourse(user: User): void{
        if(!this._users.includes(user))
         this._users.push(user);
    }

    deleteUserFromCourse(user: User) {
        this._users = this._users.filter(item => item !== user);
    }*/
}
