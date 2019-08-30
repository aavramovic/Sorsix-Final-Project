import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {catchError} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class LoginGuardService implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate() {
        try {
            if (parseInt(localStorage.getItem('expires_at')) > 0) {
                // logged in so return true
                this.router.navigate(['/']).then(() => {
                });
                return false;
            }

            // not logged in so redirect to login page
            return true;

        } catch (e) {
            throw  catchError(e);
        }
    }
}
