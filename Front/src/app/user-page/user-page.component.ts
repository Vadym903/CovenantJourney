import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { User } from '../models/user.model';
import { AuthService } from '../_services/auth-service.service';
import { UserService } from '../_services/user.service';
import { UserEditProfilePopupComponent } from './user-edit-profile-popup/user-edit-profile-popup.component';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss']
})
export class UserPageComponent implements OnInit {

  currentUser: User = null;
  user: User = null;
  userFullName = '';
  isReadMoreMode = false;
  loaded = false;
  isEditMode = false;

  constructor(private authService: AuthService,
              private userService: UserService,
              private activeRouter: ActivatedRoute,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.initUserFromUrl();
  }

  showText() {
    this.isReadMoreMode = !this.isReadMoreMode;
  }

  startToEditMode() {
    this.dialog.open(UserEditProfilePopupComponent, {data: this.user, disableClose: true, panelClass: 'user-edit-popup'})
      .afterClosed().subscribe(() => this.initUserFromUrl());
  }

  private initMainFieldsForUser(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.authService.currentUser$.subscribe(user => this.currentUser = user);
    this.userFullName = this.user.firstName + ' ' + this.user.secondName;
  }

  private initUserFromUrl(): void {
    this.userService.getById$(this.activeRouter.snapshot.params.id)
      .pipe(finalize(() => {
        this.initMainFieldsForUser();
        this.loaded = true;
      }))
      .subscribe(user => this.user = this.initUserImg(user));
  }

  private initUserImg(user: User): User {
    user.photoUrl = User.getPhotoUrl(user);
    return user;
  }
}
