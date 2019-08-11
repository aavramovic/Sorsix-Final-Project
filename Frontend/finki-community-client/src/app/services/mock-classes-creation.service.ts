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

    getMockCourses(): Observable<Set<Course>> {
        let courses: Set<Course> = new Set<Course>();
        courses.add(this.getMockCourse('Калкулус'));
        courses.add(this.getMockCourse('Дискретна Математика'));
        courses.add(this.getMockCourse('Професионални Вештини'));
        courses.add(this.getMockCourse('Структурно Програмирање'));
        courses.add(this.getMockCourse('Објектно Програмирање'));
        return of(courses);
    }

    getMockCourse(name: string): Course {
        return new Course(name, Math.random().toString(), 'Code', 'Description');
    }

    getMockCourseByCourseId(userId: string): Course {
        return new Course('name', userId, 'Code', 'Description');

    }

    getMockThreads(): Observable<Set<Thread>> {
        let threads: Set<Thread> = new Set<Thread>();
        threads.add(this.getMockThread('First Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.add(this.getMockThread('Second Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.add(this.getMockThread('Third Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.add(this.getMockThread('Fourth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
            '    quae ratione soluta? Accusantium ad dolorum expedita id labore qui rem voluptate voluptatum.'));
        threads.add(this.getMockThread('Fifth Post - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad debitis eaque eligendi ex expedita in laboriosam minus\n' +
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

    getMockUsers(): Observable<Set<User>> {
        let users: Set<User> = new Set<User>();
        users.add(this.getMockUser('John'));
        users.add(this.getMockUser('Doe'));
        users.add(this.getMockUser('Jane'));
        users.add(this.getMockUser('Doe'));
        users.add(this.getMockUser('Aang'));
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


}
