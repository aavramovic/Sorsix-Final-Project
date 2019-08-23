import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../../services/user.service';
import {Router, RouterLink} from '@angular/router';

@Component({
    selector: 'app-login-screen',
    templateUrl: './login-screen.component.html',
    styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent implements OnInit {
    hide = true;

    loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', [Validators.required, Validators.min(8)]),
    });

    constructor(private userService: UserService,
                private router: Router) {
    }

    ngOnInit() {
    }

    getErrorMessage(value: string) {
        let errors: string[] = [];

        if (this.loginForm.get(value).hasError('required')) {
            errors.push('You must enter a value');
        }

        return errors.join(', ');
    }

    onSubmit() {
        let newUser = this.loginForm;
        this.userService.loginUser(newUser);
        this.router.navigate(['/']).then(r => r.valueOf());
    }
}
