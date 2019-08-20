import {Injectable} from '@angular/core';
import {Program} from '../Models/Enumeration/Program';
import {YearOfStudy} from '../Models/Enumeration/YearOfStudy';
import {Semester} from '../Models/Enumeration/Semester';
import {Type} from '../Models/Enumeration/Type';

@Injectable({
    providedIn: 'root'
})
export class EnumService {

    constructor() {
    }

    static getPrograms(): string[] {
        return Object.keys(Program).splice(Object.keys(Program).length / 2);
    }

    static getYears() {
        return Object.values(YearOfStudy);
    }

    static getSemesters() {
        return Object.values(Semester);
    }

    static getTypes() {
        return Object.values(Type);
    }
}
