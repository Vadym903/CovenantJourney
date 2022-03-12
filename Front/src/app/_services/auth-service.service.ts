import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { InvalidLoginPopupComponent } from '../invalid-login-popup/invalid-login-popup.component';
import { AuthRequest } from '../models/auth-request.model';
import { AuthResponse } from '../models/auth-response.model';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public currentUserSubject: BehaviorSubject<User> = null;
  public currentUser$: Observable<User>;

  baseUrl = environment.serverUrl + 'auth';

  constructor(private http: HttpClient,
              private router: Router,
              private dialogRef: MatDialog) {
    this.currentUserSubject = new BehaviorSubject<User>(null);
    this.currentUser$ = this.currentUserSubject.asObservable();
  }

  public getCurrentUser$(): Observable<User> {
    return this.http.get<User>(this.baseUrl + '/current');
  }

  public getCurrentUser(): User {
    if (!this.currentUserSubject.value) {
      const user = sessionStorage.getItem('users');
      if (user) {
        this.currentUserSubject.next(JSON.parse(user) as User);
      }
    }
    return this.currentUserSubject.getValue();
  }

  public login(auth: AuthRequest) {
    return this.http.post<AuthResponse>(this.baseUrl + '/login', auth)
      .pipe(map(response => {
        this.setCookie(response);
        return response;
      })).subscribe(user => {
        this.currentUserSubject.next(user.user);
        this.router.navigateByUrl('/lots');
      }, error => this.dialogRef.open(InvalidLoginPopupComponent, { data: 'InvalidPasswordOrLogin' }));
  }

  public registrate(user: User) {
    return this.http.post(this.baseUrl + '/registration', user);
  }

  public logOut(): void {
    this.router.navigateByUrl('/');
    sessionStorage.clear();
    this.currentUserSubject.next(null);
  }

  private setCookie(response: AuthResponse): void {
    response.user = null;
    sessionStorage.setItem('userToken', response.token);
    sessionStorage.setItem('users', JSON.stringify(response.user));
  }

}
