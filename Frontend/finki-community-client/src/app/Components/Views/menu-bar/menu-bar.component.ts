import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../services/authentication.service';
import {Authorization} from '../../../Models/Enumeration/Authorization';

@Component({
    selector: 'app-menu-bar',
    templateUrl: './menu-bar.component.html',
    styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {
    isLoggedIn: boolean;
    role: string;
    username: string;

    constructor(private authService: AuthenticationService) {

    }

    ngOnInit() {
        this.isLoggedIn = AuthenticationService.isLoggedIn();
        if (this.isLoggedIn) {
            this.role = localStorage.getItem('role');
            this.username = localStorage.getItem('username');
        }
        this.authService.isLoggedIn$.subscribe(r => {
            this.isLoggedIn = r;
            if (this.isLoggedIn) {
                this.role = localStorage.getItem('role');
                this.username = localStorage.getItem('username');
            }
        });
    }

    getUsername() {
        return localStorage.getItem('username');
    }

    logout() {
        this.authService.logout();
    }

}
