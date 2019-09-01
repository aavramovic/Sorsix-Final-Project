import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
    @Input() maxsSize: number;
    @Output() pageChange: EventEmitter<number>;
    collection: any[] = [];
  constructor() {
      for(let i = 1; i <= 100; ++i){
          this.collection.push(`item${i}`);
      }
  }

  ngOnInit() {
  }

}
