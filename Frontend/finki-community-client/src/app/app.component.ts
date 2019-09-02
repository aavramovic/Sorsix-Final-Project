import {Component, Input} from '@angular/core';
import {Location} from '@angular/common';
import {UrlService} from './services/url.service';
import {switchMap} from 'rxjs/operators';
import {AuthenticationService} from './services/authentication.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'Finki Forum';

    constructor(private location: Location,
                private url: UrlService,
                private authService: AuthenticationService) {
    }
}
