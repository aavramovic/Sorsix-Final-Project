import {Injectable} from '@angular/core';
import {Course} from '../Models/Classes/Course';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {User} from '../Models/Classes/User';
import {Program} from '../Models/Enumeration/Program';
import {Type} from '../Models/Enumeration/Type';
import {Semester} from '../Models/Enumeration/Semester';

@Injectable({
    providedIn: 'root'
})
export class MockClassesCreationService {

    constructor() {
    }

    /*getMockCourses(): Observable<Course[]> {
        let courses: Course[] = [];
        courses.push(this.getMockCourse('Калкулус', 1, [Program.KNI, Program.KNIA], Type.MANDATORY, 1));
        courses.push(this.getMockCourse('Дискретна Математика', 2, [Program.IKI, Program.KNIA], Type.OPTIONAL, 2));
        courses.push(this.getMockCourse('Професионални Вештини', 3, [Program.KNI, Program.MT], Type.MANDATORY, 3));
        courses.push(this.getMockCourse('Структурно Програмирање', 4, [Program.IKI, Program.MT], Type.OPTIONAL, 4));
        courses.push(this.getMockCourse('Објектно Програмирање', 1, [Program.MT, Program.KNIA], Type.OPTIONAL, 5));
        courses.push(new Course('Веројатност и Статистика', 1, 'CSEW101',
            'Многу ez предмет', 2, [Program.KNI, Program.KNIA], Semester.SUMMER, Type.MANDATORY));
        return of(courses);
    }*/

    /*getMockCourse(name: string, yearOfStudy: number, program: Program[], type: Type, courseId: number): Course {
        return new Course(name, courseId, 'Code', 'Description', yearOfStudy, program, Semester.WINTER, type);
    }*/

    /*getMockCourseByCourseId(userId: number): Course {
        return new Course('name', userId, 'Code', 'Description', 1, [Program.IKI], Semester.WINTER, Type.MANDATORY);

    }*/

    /*getMockThreads(): Observable<Thread[]> {
        let threads: Thread[] = [];
        threads.push(this.getMockThread('First Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.', 5));
        threads.push(this.getMockThread('Second Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.', 6));
        threads.push(this.getMockThread('Third Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.', 5));
        threads.push(this.getMockThread('Fourth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.', 7));
        threads.push(this.getMockThread('Fifth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.', 8));
        return of(threads);
    }*/
/*

    getMockThread(content: string, courseId: number): Thread {
        return new Thread(
            Math.round(Math.random() * 100),
            Math.round(Math.random() * 100).toString(),
            courseId.toString(),
            new Date('1970-01-01'),
            content,
            'Title');
    }
*/
/*
    getMockUsers(): Observable<User[]> {
        let users: User[] = [];
        users.push(this.getMockUser('John'));
        users.push(this.getMockUser('Doe'));
        users.push(this.getMockUser('Jane'));
        users.push(this.getMockUser('Doe'));
        users.push(this.getMockUser('Aang'));
        return of(users);
    }*/

/*
    getMockUser(username: string): User {
        let userId = Math.round(Math.random() * 100);
        return new User(
            userId + '-firstName',
            userId + '-lastName',
            username,
            'password',
            userId + '-email',
            userId);
    }
*/

/*    getMockUserByUserId(userId: number): User {
        return new User(
            userId + '-firstName',
            userId + '-lastName',
            userId + '-username',
            'password',
            userId + '-email',
            userId);
    }*/

    /*async delay() {
        await new Promise(resolve => setTimeout(() => resolve(), 150)).then(() => {});
    }*/
}
