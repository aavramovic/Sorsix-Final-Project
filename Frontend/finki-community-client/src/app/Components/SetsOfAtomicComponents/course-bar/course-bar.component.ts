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
import {switchMap} from 'rxjs/operators';

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

    year: string;
    program: string;
    semester: string;
    type: string;

    filter = new Subject();

    constructor(private courseService: CourseService,
                private mock: MockClassesCreationService) {
    }

    ngOnInit() {
        this.filter.pipe(switchMap(() => this.courseService.getCourses(this.program, this.year, this.semester, this.type)))
            .subscribe(courses => this.courses = courses);
        this.filter.next();
    }

    filterCourses() {
        this.filter.next();
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


}

