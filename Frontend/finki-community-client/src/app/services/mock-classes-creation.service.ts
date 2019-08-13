import {Injectable} from '@angular/core';
import {Course} from '../Models/Classes/Course';
import {Observable, of} from 'rxjs';
import {Thread} from '../Models/Classes/Thread';
import {User} from '../Models/Classes/User';

@Injectable({
    providedIn: 'root'
})
export class MockClassesCreationService {

    constructor() {
    }

    getMockCourses(): Observable<Course[]> {
        let courses: Course[] = [];
        courses.push(this.getMockCourse('Калкулус'));
        courses.push(this.getMockCourse('Дискретна Математика'));
        courses.push(this.getMockCourse('Професионални Вештини'));
        courses.push(this.getMockCourse('Структурно Програмирање'));
        courses.push(this.getMockCourse('Објектно Програмирање'));
        return of(courses);
    }

    getMockCourse(name: string): Course {
        return new Course(name, Math.random().toString(), 'Code', 'Description');
    }

    getMockCourseByCourseId(userId: string): Course {
        return new Course('name', userId, 'Code', 'Description');

    }

    getMockThreads(): Observable<Thread[]> {
        let threads: Thread[] = [];
        threads.push(this.getMockThread('First Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.push(this.getMockThread('Second Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.push(this.getMockThread('Third Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.push(this.getMockThread('Fourth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.push(this.getMockThread('Fifth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        return of(threads);
    }

    getMockThread(content: string): Thread {
        return new Thread(
            Math.round(Math.random() * 100).toString(),
            Math.round(Math.random() * 100).toString(),
            Math.round(Math.random() * 100).toString(),
            new Date('1970-01-01'),
            content,
            'Title');
    }

    getMockUsers(): Observable<User[]> {
        let users: User[] = [];
        users.push(this.getMockUser('John'));
        users.push(this.getMockUser('Doe'));
        users.push(this.getMockUser('Jane'));
        users.push(this.getMockUser('Doe'));
        users.push(this.getMockUser('Aang'));
        return of(users);
    }

    getMockUser(username: string): User {
        let userId = Math.round(Math.random() * 100).toString();
        return new User(
            userId + '-firstName',
            userId + '-lastName',
            username,
            'password',
            userId + '-email',
            userId);
    }

    getMockUserByUserId(userId: string): User {
        return new User(
            userId + '-firstName',
            userId + '-lastName',
            userId + '-username',
            'password',
            userId + '-email',
            userId);
    }

    async delay() {
        await new Promise(resolve => setTimeout(() => resolve(), 150)).then(() => console.log('fired'));
    }
}
