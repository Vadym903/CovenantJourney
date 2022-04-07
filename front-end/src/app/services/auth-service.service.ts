import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from "../_models/user.model";
import { AuthRequest } from "../_models/auth-request.model";
import { AuthResponse } from "../_models/auth-response.model";
import { CookieStorage } from "cookie-storage";

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    public currentUserSubject: BehaviorSubject<User | null> = new BehaviorSubject<User>(null);

    baseUrl = environment.serverUrl + 'auth';

    constructor(private http: HttpClient,
                private router: Router,
                private cookieStorage: CookieStorage) {
    }

    public getCurrentUser$(): Observable<User> {
        return this.http.get<User>(this.baseUrl + '/current');
    }

    public getCurrentUser(): User {
        if (!this.currentUserSubject.value) {
            const user = this.cookieStorage.getItem('user');
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
        this.currentUserSubject.next(null);
    }

    private onLogin(response: AuthResponse): AuthResponse {
        this.cookieStorage.setItem('userToken', response.token);
        this.cookieStorage.setItem('user', JSON.stringify(response.user));
        this.currentUserSubject.next(response.user);
        return response;
    }

}
