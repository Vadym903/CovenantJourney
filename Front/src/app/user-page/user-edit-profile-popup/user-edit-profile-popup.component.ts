import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { finalize, map, switchMap } from 'rxjs/operators';
import { User } from 'src/app/models/user.model';
import { PhotoService } from 'src/app/_services/photo.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-user-edit-profile-popup',
  templateUrl: './user-edit-profile-popup.component.html',
  styleUrls: ['./user-edit-profile-popup.component.scss']
})
export class UserEditProfilePopupComponent implements OnInit {

  userForm: FormGroup;
  currentUser = null;
  uploadedImageFile: File;

  constructor(@Inject(MAT_DIALOG_DATA) user: User,
              private userService: UserService,
              private photoService: PhotoService,
              private dialogRef: MatDialogRef<UserEditProfilePopupComponent>,
              private formBuilder: FormBuilder) {
    this.currentUser = user;
    this.initFormFields(user);
  }

  ngOnInit(): void {
  }

  catchImage(image: File) {
    this.uploadedImageFile = image;
  }

  updateUser() {
    const newUser = this.buildUserFromForm();
    this.userService.update$(this.currentUser.id, newUser)
      .pipe(
        finalize( () => this.close()),
        switchMap(user => this.checkPhoto(user)))
      .subscribe(user => this.afterSave(user));
  }

  close() {
    this.dialogRef.close();
  }

  private afterSave(user): void {
    this.userService.update$(user.id, user).pipe()
      .subscribe();
  }

  private checkPhoto(user: User): Observable<User> {
    if (this.uploadedImageFile) {
      return this.photoService.create$(this.uploadedImageFile).pipe(map(photo => {
        user.photoId = +photo.id;
        return user;
      }));
    }
    return of(user);
  }

  private buildUserFromForm(): User {
    const user = this.currentUser;
    const fromValue = this.userForm.value;
    user.login = fromValue.login;
    user.firstName = fromValue.firstName;
    user.secondName = fromValue.lastName;
    user.email = fromValue.email;
    user.description = fromValue.description;
    return user;
  }

  private initFormFields(user: User): void {
    this.userForm = this.formBuilder.group({
      login: [user.login, [Validators.required, Validators.maxLength(30)]],
      firstName: [user.firstName, [Validators.required, Validators.maxLength(30)]],
      lastName: [user.secondName, [Validators.required, Validators.maxLength(30)]],
      email: [user.email, [Validators.required, Validators.maxLength(30)]],
      description: [user.description || '', Validators.maxLength(500)]
    });
  }
}
