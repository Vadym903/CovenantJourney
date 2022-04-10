import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";
import { AuthService } from "../../services/auth-service.service";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AuthRequest } from "../../_models/auth-request.model";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    authForm: FormGroup;

    constructor(private dialogRef: MatDialogRef<LoginComponent>,
                private authService: AuthService,
                private fb: FormBuilder) {
        this.initForm();
    }

    ngOnInit(): void {
    }

    private initForm(): void {
        this.authForm = this.fb.group({
            login: this.fb.control(''),
            password: this.fb.control('')
        });
    }

    login(): void {
        const formValue = this.authForm.value;
        const authRequest = new AuthRequest(formValue.login, formValue.password);
        this.authService.login(authRequest).subscribe(() => this.dialogRef.close());
    }
}
