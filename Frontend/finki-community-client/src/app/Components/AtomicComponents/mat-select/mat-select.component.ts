import {Component, Input, OnInit, Output} from '@angular/core';
import {Subject} from 'rxjs';
import {MatOptionSelectionChange} from '@angular/material';

@Component({
    selector: 'app-mat-select',
    templateUrl: './mat-select.component.html',
    styleUrls: ['./mat-select.component.css']
})
export class MatSelectComponent implements OnInit {
    @Input()
    options: string[];
    @Input()
    optionDescription: string;
    @Output()
    option = new Subject<string>();

    constructor() {
    }

    ngOnInit() {
    }

    setOption(option: MatOptionSelectionChange) {
        if (option && option.source.selected === true) {
            this.option.next(option.source.value ? option.source.value : null);
        }
    }
}
