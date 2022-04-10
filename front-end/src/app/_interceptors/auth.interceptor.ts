import { environment } from '../../environments/environment';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from "../services/auth-service.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url.startsWith(`${environment.serverUrl}`)) {
            if (sessionStorage.getItem('userToken')) {
                req = req.clone({
                    setHeaders: {
                        Authorization: `Bearer ${sessionStorage.getItem('userToken')}`
                    }
                });
            }
        }

        return next.handle(req).pipe(catchError(error => this.handleErrors(error)));

    }

    handleErrors(error: any): Observable<never> {
        if (error.status === 0) {
            this.authService.logOut();
        }
        return throwError(error);
    }

}
