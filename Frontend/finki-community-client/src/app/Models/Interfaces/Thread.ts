import {Input} from '@angular/core';
import {Course} from '../Classes/Course';
import {User} from '../Classes/User';

export interface Thread {
  noOfLikes: number;
  title: string;
  timeOfPost: string;
  content: string;

  postId: number;
  userId: number;
  courseId: number;
}
