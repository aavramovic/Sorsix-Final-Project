import {Input} from '@angular/core';
import {Course} from '../Classes/Course';
import {User} from '../Classes/User';

export interface Thread {
  timeOfPost: string;
  content: string;
  course: Course;
  user: User
}
