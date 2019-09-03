import {Injectable} from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

import {AuthenticationService} from './authentication.service';
import {catchError} from 'rxjs/operators';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = this.authenticationService.getToken();
        if (AuthenticationService.isLoggedIn()) {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.authenticationService.getToken()}`
                }
            });
        }
        return next.handle(request)
            .pipe(catchError(error => {
                if (error.status === 401) {
                    this.authenticationService.logout();
                }
                return throwError(error);
            }));
    }
}
