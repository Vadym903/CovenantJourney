import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";


@Component({
    selector: 'app-modify-apartment-dialog',
    templateUrl: './modify-apartment-dialog.component.html',
    styleUrls: ['./modify-apartment-dialog.component.scss']
})
export class ModifyApartmentDialogComponent implements OnInit {

    constructor(private dialog: MatDialogRef<ModifyApartmentDialogComponent>) {
    }

    ngOnInit(): void {
    }

    close(): void {
        this.dialog.close();
    }

}
