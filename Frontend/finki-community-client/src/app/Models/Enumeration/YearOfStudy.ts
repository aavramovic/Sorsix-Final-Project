import {map} from 'rxjs/operators';

export enum YearOfStudy {
    FRESHMAN = 'First Year',
    SOPHOMORE = 'Second Year',
    JUNIOR = 'Third Year',
    SENIOR = 'Fourth Year',

}


export function getKeyByValue(value: string) {
    if (value == 'First Year') {
        return 'FRESHMAN';
    }
    if (value == 'Second Year') {
        return 'SOPHOMORE';
    }
    if (value == 'Third Year') {
        return 'JUNIOR';
    }
    if (value == 'Fourth Year') {
        return 'SENIOR';
    }
    return  "ERROR";
}
