import {User} from './User';

export class Course {
    courseName: string;
    private courseId: number;
    code: string;
    description: string;
    //ManyToMany
    private _users: Set<User>;

    //test constructor
    constructor(name: string, courseId: number, code: string, description: string) {
        this.courseName = name;
        this.courseId = courseId;
        this.code = code;
        this.description = description;
        this._users = new Set<User>();
    }

    get users(): Set<User> {
        return this._users;
    }

    addUserToCourse(user: User): void{
        if(!this._users.has(user))
         this._users.add(user);
    }

    deleteUserFromCourse(user: User): boolean{
        return this._users.delete(user);
    }
}
