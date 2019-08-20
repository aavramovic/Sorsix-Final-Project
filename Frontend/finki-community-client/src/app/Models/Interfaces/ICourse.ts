import {YearOfStudy} from '../Enumeration/YearOfStudy';

export interface ICourse {
    courseId: number;
    code: string;
    courseName: string;
    courseDescription: string;

    studyYear: string;
    semester: string;
    program: string;
    courseType: string;
}
