import { Component, OnInit } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { LoginComponent } from "../login/login.component";
import { User } from "../../_models/user.model";
import { AuthService } from "../../services/auth-service.service";

@Component({
    selector: 'app-nav-bar',
    templateUrl: './nav-bar.component.html',
    styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

    user: User;

    constructor(private dialogRef: MatDialog, private authService: AuthService) {
    }

    ngOnInit(): void {
        this.user = this.authService.getCurrentUser();
        this.authService.currentUserSubject.subscribe(user => this.user = user);
    }

    openLoginPopup(): void {
        this.dialogRef.open(LoginComponent, {panelClass: 'login-popup'});
    }

    logOut(): void {
        this.authService.logOut();
    }

}
