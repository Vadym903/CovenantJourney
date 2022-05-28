import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from "../_models/user.model";
import { AuthRequest } from "../_models/auth-request.model";
import { AuthResponse } from "../_models/auth-response.model";
import { CookieService } from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    public currentUserSubject: BehaviorSubject<User | null> = new BehaviorSubject<User>(null);

    baseUrl = environment.serverUrl + 'auth';

    constructor(private http: HttpClient,
                private router: Router,
                private cookieService: CookieService) {
    }

    public getCurrentUser$(): Observable<User> {
        return this.http.get<User>(this.baseUrl + '/current');
    }

    public getCurrentUser(): User {
        if (!this.currentUserSubject.value) {
            const user = localStorage.getItem('user');
            if (user) {
                this.currentUserSubject.next(User.fromObject(JSON.parse(user) as User));
            }
        }
        return this.currentUserSubject.getValue();
    }

    public login(auth: AuthRequest) {
        return this.http.post<AuthResponse>(this.baseUrl + '/login', auth)
            .pipe(map(response => this.onLogin(response)))
    }

    public registrate(user: User) {
        return this.http.post(this.baseUrl + '/registration', user);
    }

    public logOut(): void {
        this.router.navigateByUrl('/');
        sessionStorage.clear();
        this.cookieService.deleteAll();
        this.currentUserSubject.next(null);
        localStorage.removeItem('user');
    }

    private onLogin(response: AuthResponse): AuthResponse {
        const user = response.user;
        user.description = "";
        this.cookieService.set('userToken', response.token);
        localStorage.setItem('user', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return response;
    }

}
