import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-invalid-login-popup',
  templateUrl: './invalid-login-popup.component.html',
  styleUrls: ['./invalid-login-popup.component.scss']
})
export class InvalidLoginPopupComponent implements OnInit {

  mainMessage = '';
  constructor(@Inject(MAT_DIALOG_DATA) message: string,
    private dialogRef: MatDialogRef<InvalidLoginPopupComponent>) {
    this.mainMessage = message;
  }

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.close();
  }

}
