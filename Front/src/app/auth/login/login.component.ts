import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/models/auth-request.model';
import { AuthService } from 'src/app/_services/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  floatLabelControl = new FormControl('auto');
  
  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
    this.loginForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.minLength(4)]],
      password: ['',  [Validators.required, Validators.minLength(6)]]
    });
    sessionStorage.clear();

  }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.buildAuthRequest());
  }

  private buildAuthRequest(): AuthRequest {
    const formValue = this.loginForm.value;
    return {login: formValue.login,
            password: formValue.password};
  }

}
