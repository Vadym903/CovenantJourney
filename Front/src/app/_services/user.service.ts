import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user.model';
import { AbstractApiService } from './abstract-api-service';
import { AuthService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractApiService<User> {
 
  constructor(public http: HttpClient,private authService: AuthService) {
    super(http);
  }

  public getURL(): string {
    return 'user';
  }

  update$(id: number, entity: User): Observable<User> {
    return this.http.post<User>(this.apiPath + `/${id}`, entity).pipe(map(user => {
      this.authService.currentUserSubject.next(user);
      sessionStorage.setItem('users', JSON.stringify(user));
      return user;
    }));
}
}
