import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-register-screen',
    templateUrl: './register-screen.component.html',
    styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {
    hidePassword: boolean = true;
    hideConfirmPassword: boolean = true;

    registerForm = new FormGroup({
        username: new FormControl('', Validators.required),
        email: new FormControl('', [Validators.required, Validators.email]),
        confirmEmail: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required, Validators.min(8)]),
        confirmPassword: new FormControl('', [Validators.required]),
        firstName: new FormControl('', [Validators.required]),
        lastName: new FormControl('', [Validators.required]),
        birthdate: new FormControl((new Date(1998, 12, 4)).getMilliseconds(), [Validators.required]),
    }, {
        validators: [
            this.matchValidator('email', 'confirmEmail'),
            this.matchValidator('password', 'confirmPassword')
        ]
    });

    private matchValidator(value, confirmValue): ValidatorFn {
        return (group: FormGroup): ValidationErrors => {
            const val = group.get(value);
            const confirmVal = group.get(confirmValue);
            if (!val || !confirmVal) {
                return;
            }
            if (val.value !== confirmVal.value) {
                confirmVal.setErrors({notEquivalent: true});
            } else {
                confirmVal.setErrors(null);
            }
            return;
        };
    }

    constructor(private http: HttpClient,
                private userService: UserService,
                private router: Router) {
    }


    ngOnInit() {
    }

    getErrorMessage(value: string) {
        let errors: string[] = [];

        if (this.registerForm.get(value).hasError('required')) {
            errors.push('You must enter a value');
        }
        if (this.registerForm.get(value).hasError('email')) {
            errors.push('Not a valid email');
        }
        if (this.registerForm.get(value).hasError('notEquivalent')) {
            errors.push('Does not match');
        }
        return errors.join(', ');
    }

    onSubmit() {
        let newUser = this.registerForm;
        newUser.removeControl('confirmEmail');
        newUser.removeControl('confirmPassword');
        this.userService.postNewUser(newUser);
        this.router.navigate(['/']).then(r => r.valueOf());
    }
}
