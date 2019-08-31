import {Component, OnInit} from '@angular/core';
import {CourseService} from '../../../services/course.service';
import {Course} from '../../../Models/Classes/Course';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {EnumService} from '../../../services/enum.service';

@Component({
    selector: 'app-courses',
    templateUrl: './course-bar.component.html',
    styleUrls: ['./course-bar.component.css']
})
export class CourseBarComponent implements OnInit {
    courses: Course[];
    programs: string[] = EnumService.getPrograms();
    years: string[] = EnumService.getYears();
    semesters: string[] = EnumService.getSemesters();
    mandatory: string[] = EnumService.getTypes();

    year: string;
    program: string;
    semester: string;
    type: string;

    filter$ = new Subject();

    constructor(private courseService: CourseService,
                private enumService: EnumService) {
    }

    ngOnInit() {
        this.filter$.pipe(switchMap(() => this.courseService.getCourses(this.program, this.year, this.semester, this.type)))
            .subscribe(courses => this.courses = courses);
        this.filter$.next();
    }

    filterCourses() {
        this.filter$.next();
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

