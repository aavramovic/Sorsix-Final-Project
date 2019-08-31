import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../../services/user.service';
import {Router} from '@angular/router';
import {Subject, throwError} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {PostUser} from '../../../Models/Classes/PostUser';

@Component({
    selector: 'app-register-screen',
    templateUrl: './register-screen.component.html',
    styleUrls: ['./register-screen.component.css']
})
export class RegisterScreenComponent implements OnInit {
    hidePassword: boolean = true;
    hideConfirmPassword: boolean = true;
    user$ = new Subject();
    newUser: PostUser;
    today: Date;

    registerForm = new FormGroup({
        username: new FormControl('Antonio', Validators.required),
        email: new FormControl('asd@asd', [Validators.required, Validators.email]),
        password: new FormControl('12345678', [Validators.required, Validators.minLength(8)]),
        confirmPassword: new FormControl('12345678', [Validators.required]),
        firstName: new FormControl('asd', [Validators.required]),
        lastName: new FormControl('asd', [Validators.required]),
        birthdate: new FormControl('', [Validators.required]),
        sex: new FormControl('', [Validators.required])
    }, {
        validators: [
            this.matchValidator('password', 'confirmPassword'),
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
        this.registerForm.clearValidators();

        this.user$.pipe(switchMap(() =>
            this.userService.postNewUser(this.newUser)))
            .subscribe(response => {
                    if (response.valid) {
                        this.router.navigate(['/']).then(r => r.valueOf());
                    } else {
                        alert('This account exists and has been disabled');
                    }
                });


        this.today = new Date();
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
        if (this.registerForm.get(value).hasError('minLength')) {
            errors.push('Password too short');
        }

        return errors.join(', ');
    }

    onSubmit() {
        this.newUser = new PostUser(
            this.registerForm.get('username').value,
            this.registerForm.get('firstName').value,
            this.registerForm.get('lastName').value,
            this.registerForm.get('password').value,
            new Date(this.registerForm.get('birthdate').value).getMilliseconds(),
            this.registerForm.get('email').value,
            this.registerForm.get('sex').value
        );

        this.user$.next();
    }
}
