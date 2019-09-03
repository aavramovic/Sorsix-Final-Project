import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';
import {Subject} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material';

@Component({
    selector: 'app-login-screen',
    templateUrl: './login-screen.component.html',
    styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent implements OnInit {
    hide = true;
    login$ = new Subject();
    location: any = location;
    loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    });
    username: string = '';
    password: string = '';

    constructor(private userService: UserService,
                private router: Router,
                private _snackBar: MatSnackBar,
                private authService: AuthenticationService) {

    }

    ngOnInit() {
        this.subject();
    }


    subject() {
        this.login$.pipe(switchMap(() =>
            this.authService.login(this.username, this.password))).subscribe(response => {
                this.router.navigate(['/']).then(r => r.valueOf());

            },
            error => {
                this.openSnackBar('Username or password is incorrect');
            });
    }

    openSnackBar(message: string) {
        this._snackBar.open(message, 'Close', {
            duration: 3000
        });
    }

    getErrorMessage(value: string) {
        let errors: string[] = [];

        if (this.loginForm.get(value).hasError('required')) {
            errors.push('You must enter a value');
        }
        if (this.loginForm.get(value).hasError('minlength')) {
            errors.push('Password too short');
        }

        return errors.join(', ');
    }

    onSubmit() {
        this.username = this.loginForm.get('username').value;
        this.password = this.loginForm.get('password').value;
        this.login$.next();
        this.subject();
    }
}
