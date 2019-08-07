import { Component, OnInit } from '@angular/core';
import {Thread} from '../../../Models/Interfaces/Thread';
import {Course} from '../../../Models/Classes/Course';
import {User} from '../../../Models/Classes/User';

@Component({
  selector: 'app-threads',
  templateUrl: './threads.component.html',
  styleUrls: ['./threads.component.css']
})
export class ThreadsComponent implements OnInit {

  constructor(thread: Thread) {
  }

  ngOnInit() {
  }

}
