import { Component, OnInit } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { LoginComponent } from "../login/login.component";

@Component({
    selector: 'app-nav-bar',
    templateUrl: './nav-bar.component.html',
    styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

    constructor(private dialogRef: MatDialog) {
    }

    ngOnInit(): void {
    }

    openLoginPopup(): void {
        this.dialogRef.open(LoginComponent, {panelClass: 'login-popup'});
    }

}
