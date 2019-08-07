import {Thread} from '../Interfaces/Thread';
import {Course} from './Course';
import {User} from './User';
import {Input} from '@angular/core';

export class ReplyThread implements Thread{
  content: string;
  timeOfPost: string;
  @Input()
  course: Course;
  @Input()
  user: User;

}
