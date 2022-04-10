import { Component, OnInit } from '@angular/core';
import { ApartmentService } from "../../services/apartment.service";
import { MatDialog } from "@angular/material/dialog";
import { ModifyApartmentDialogComponent } from "./modify-apartment-dialog/modify-apartment-dialog.component";

@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.scss']
})
export class ApartmentsComponent implements OnInit {

    constructor(private apartmentService: ApartmentService, private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.apartmentService.getPage$().subscribe(page => console.log(page));
    }

    openCreateWindow(): void {
        this.dialog.open(ModifyApartmentDialogComponent, {panelClass: 'modify-apartment-dialog'});
    }

}
