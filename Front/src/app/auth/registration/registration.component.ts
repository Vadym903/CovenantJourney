import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { InvalidLoginPopupComponent } from 'src/app/invalid-login-popup/invalid-login-popup.component';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/_services/auth-service.service';
import { FormErrorsUtils } from 'src/app/_utils/form-errors-utils';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;
  formErrorsUtils: FormErrorsUtils = new FormErrorsUtils();

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private dialogRef: MatDialog) {

    this.registrationForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40)]],
      firstName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40)]],
      lastName: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40)]],
      email: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(40), Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(30)]]
    });
  }

  ngOnInit(): void {
  }

  registrate(): void {
    this.authService.registrate(this.buildUserFromForm())
      .subscribe(() => this.router.navigate(['/login']), error => {
        this.dialogRef.open(InvalidLoginPopupComponent, { data: 'InvalidLogin' });
      });
  }

  private buildUserFromForm(): User {
    const formValue = this.registrationForm.value;
    return {
      firstName: formValue.firstName,
      secondName: formValue.lastName,
      login: formValue.login,
      password: formValue.password,
      email: formValue.email
    };
  }

}
