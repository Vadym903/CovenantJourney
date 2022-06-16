import { environment } from '../../environments/environment';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor() {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url.startsWith(`${environment.serverUrl}`)) {
            const userToken = sessionStorage.getItem('userToken');
            if (userToken) {
                req = req.clone({
                    setHeaders: {
                        Authorization: `Bearer ${userToken}`
                    }
                });
            }
        }

        return next.handle(req).pipe(catchError(error => this.handleErrors(error)));

    }

    handleErrors(error: any): Observable<never> {
        console.log(error.status);
        // if (error.status === 0) {
        //     this.authService.logOut();
        // }
        return throwError(error);
    }

}
