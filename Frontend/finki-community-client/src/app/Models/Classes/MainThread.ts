import {Thread} from '../Interfaces/Thread';
import {Course} from './Course';
import {User} from './User';
import {Input} from '@angular/core';

export class MainThread implements Thread{
  title: string;
  content: string;
  timeOfPost: string;
  @Input()
  course: Course;
  @Input()
  readonly user: User;

}
