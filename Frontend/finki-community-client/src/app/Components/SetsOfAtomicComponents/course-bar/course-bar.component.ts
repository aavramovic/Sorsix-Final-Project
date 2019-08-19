import {Component, OnInit, Output} from '@angular/core';
import {CourseService} from '../../../services/course.service';
import {Course} from '../../../Models/Classes/Course';
import {MockClassesCreationService} from '../../../services/mock-classes-creation.service';
import {YearOfStudy} from '../../../Models/Enumeration/YearOfStudy';
import {Program} from '../../../Models/Enumeration/Program';
import {Type} from '../../../Models/Enumeration/Type';
import {Subject} from 'rxjs';
import {Semester} from '../../../Models/Enumeration/Semester';
import {toTitleCase} from 'codelyzer/util/utils';

@Component({
    selector: 'app-courses',
    templateUrl: './course-bar.component.html',
    styleUrls: ['./course-bar.component.css']
})
export class CourseBarComponent implements OnInit {
    courses: Course[];
    programs: string[] = Object.keys(Program).splice(Object.keys(Program).length / 2);
    years: string[] = Object.values(YearOfStudy);
    semesters: string[] = Object.values(Semester);
    mandatory: string[] = Object.values(Type);
    filteredCourses: Course[];
    year: string;
    program: string;
    semester: string;
    type: string;
    @Output()
    selectedCourse = new Subject<Course>();

    constructor(private courseService: CourseService,
                private mock: MockClassesCreationService) {
    }

    ngOnInit() {
        this.courseService.getCourses().subscribe(courses => this.courses = courses);
    }

    filterCourses() {
        this.filteredCourses = this.courses;

        // console.log('Before filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);

        this.filteredCourses = this.year ?
            this.filteredCourses.filter(course => course.yearOfStudy.toString() == this.year)
            : this.filteredCourses;

        // console.log('After year filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);

        this.filteredCourses = this.program ?
            this.filteredCourses.filter(course => course.program.includes(Program[this.program]))
            : this.filteredCourses;

        // console.log('After program filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);

        this.filteredCourses = this.semester ?
            this.filteredCourses.filter(course => course.semester.toString() == this.semester)
            : this.filteredCourses;

        //console.log('After semester filter - " + this.filteredCourses.length + ' : ' + this.filteredCourses);

        this.filteredCourses = this.type ?
            this.filteredCourses.filter(course => course.type.toString() == this.type)
            : this.filteredCourses;

        // console.log('After type filter - ' + this.filteredCourses.length + ' : ' + this.filteredCourses);
        // this.filteredCourses.forEach(course => console.log(course));
    }

    setProgram($event?: string) {
        this.program = $event;
        // console.log('Program: ' + this.program);
    }

    setYear($event?: string) {
        this.year = $event;
        // console.log('Year: ' + this.year);
    }

    setSemester($event: string) {
        this.semester = $event;
    }

    setType($event?: string) {
        this.type = $event;
        // console.log('Type: ' + this.type);
    }

    setSelectedCourse(course: Course) {
        this.selectedCourse.next(course);
    }

}

