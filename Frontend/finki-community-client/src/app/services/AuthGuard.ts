import { Injectable } from '@angular/core';
import { Router, ActivatedRoute, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router,
                private actvRoute: ActivatedRoute) { }

    canActivate() {
        console.log('AuthGuard.CanActivate');
        //return true;

        try {
            if (localStorage.getItem('currentUser')) {
                // logged in so return true
                return true;
            }

            // not logged in so redirect to login page
            this.router.navigate(['/login']);
            return false;

        } catch (e) {

        }
    }
}
