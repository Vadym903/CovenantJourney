import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "../../services/auth-service.service";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthRequest } from "../../_models/auth-request.model";
import { UserRole } from "../../_models/_enums/user-role.enum";

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	authForm: FormGroup;
	registrationForm: FormGroup;
	isLoginMode = true;
	isLandLord = false;

	constructor(private dialogRef: MatDialogRef<LoginComponent>,
				@Inject(MAT_DIALOG_DATA) public data: any,
				private authService: AuthService,
				private fb: FormBuilder) {
	}

	ngOnInit(): void {
		this.initForm();
	}

	private initForm(): void {
		console.log(this.data.isLandlord);
		if (this.data.isLandlord) {
			this.initRegistrationForm();
			this.isLoginMode = false;
			this.isLandLord = true;
		} else {
			this.initLoginForm();
		}
	}

	private initLoginForm() {
		this.authForm = this.fb.group({
			login: this.fb.control(''),

			password: this.fb.control('')
		});
	}

	private initRegistrationForm(): void {
		this.registrationForm = this.fb.group({
			login: this.fb.control(''),
			password: this.fb.control(''),
			fullName: this.fb.control(''),
			email: this.fb.control(''),
			userRole: this.fb.control(this.isLandLord ? UserRole.ROLE_MANAGER : UserRole.ROLE_USER),
			description: this.fb.control('')
		});
		this.registrationForm.addControl('control.name', this.fb.control('', []));
	}

	login(): void {
		const formValue = this.authForm.value;
		const authRequest = new AuthRequest(formValue.login, formValue.password);
		this.authService.login(authRequest).subscribe(() => this.dialogRef.close());
	}

	switchMode(isLoginMode: boolean): void {
		this.isLoginMode = isLoginMode;

		if (isLoginMode) {
			this.initLoginForm();
		} else {
			this.initRegistrationForm();
		}
	}

	close() {
		this.dialogRef.close();
	}
}
