import {Component, OnInit} from '@angular/core';
import {CourseService} from '../../../services/course.service';
import {Course} from '../../../Models/Classes/Course';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {YearOfStudy} from '../../../Models/Enumeration/YearOfStudy';
import {Program} from '../../../Models/Enumeration/Program';
import {Mandatory} from '../../../Models/Enumeration/Mandatory';
import {of} from 'rxjs';

@Component({
    selector: 'app-courses',
    templateUrl: './course-bar.component.html',
    styleUrls: ['./course-bar.component.css']
})
export class CourseBarComponent implements OnInit {
    courses: Course[];
    programs: string[] = Object.keys(Program).splice(Object.keys(Program).length / 2);
    years: string[] = Object.keys(YearOfStudy).splice(Object.keys(YearOfStudy).length / 2);
    mandatory: string[] = Object.keys(Mandatory).splice(Object.keys(Mandatory).length / 2);
    filteredCourses: Course[];
    year: string;
    program: string;
    type: string;

    constructor(private courseService: CourseService,
                private mock: MockClassesCreationService) {
    }

    ngOnInit() {

        //TODO:// trgni go delayot
        this.mock.delay().then(() =>
            this.courseService.getMockCourses().subscribe(courses => this.courses = courses));
        // this.courseService.getMockCourses().subscribe(courses => this.courses = courses));

    }

    filterCourses() {
        this.filteredCourses = this.courses;
        console.log('Before filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);
        this.filteredCourses = this.year ? this.filteredCourses.filter(course => course.yearOfStudy.toString() == this.year) : this.filteredCourses;
        console.log('After year filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);
        this.filteredCourses = this.program ? this.filteredCourses.filter(course => course.program.includes(Program[this.program])) : this.filteredCourses;
        console.log('After program filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);
        this.filteredCourses = this.type ? this.filteredCourses.filter(course => course.isMandatory.toString() == this.type) : this.filteredCourses;
        console.log('After type filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);

    }

    setProgram($event?: string) {
        this.program = $event;
        console.log('Program: ' + this.program);
    }

    setYear($event?: string) {
        this.year = $event;
        console.log('Year: ' + this.year);
    }

    setType($event?: string) {
        this.type = $event;
        console.log('Type: ' + this.type);
    }
}

