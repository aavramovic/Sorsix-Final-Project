import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {Authorization} from '../../../Models/Enumeration/Authorization';

@Component({
    selector: 'app-menu-bar',
    templateUrl: './menu-bar.component.html',
    styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {
    isLoggedIn: boolean = false;
    // token: string;
    role: string;

    constructor(private authService: AuthenticationService) {
    }

    ngOnInit() {
        this.authService.isLoggedIn$.subscribe(r => {
            this.isLoggedIn = r;
            this.role = localStorage.getItem('role');
        });
    }

    logout() {
        console.log('Logout');
        this.authService.logout();
    }

}
