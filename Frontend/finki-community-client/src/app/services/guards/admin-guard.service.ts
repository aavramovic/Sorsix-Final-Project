import {Injectable} from '@angular/core';
import {Router, CanActivate} from '@angular/router';
import {catchError} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AdminGuardService implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate() {
        try {
            if (localStorage.getItem('role') == 'ADMIN') {
                // logged in so return true
                return true;
            }

            // not logged in so redirect to login page
            this.router.navigate(['/']).then(() => {
            });
            return false;

        } catch (e) {
            throw  catchError(e);
        }
    }
}
