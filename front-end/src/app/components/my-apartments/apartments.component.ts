import { Component, OnInit } from '@angular/core';
import { ApartmentService } from "../../services/apartment.service";
import { MatDialog } from "@angular/material/dialog";
import { ModifyApartmentDialogComponent } from "./modify-apartment-dialog/modify-apartment-dialog.component";
import { Apartment } from "../../_models/apartment.model";
import { EMPTY } from "rxjs";

@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.scss']
})
export class ApartmentsComponent implements OnInit {

    apartments: Apartment[] = [];

    constructor(private apartmentService: ApartmentService, private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.initPage();
    }

    openCreateWindow(): void {
        this.openPopup(null);
    }

    openEditWindow(apartment: Apartment): void {
        this.openPopup(apartment);
    }

    private openPopup(apartment: Apartment): void {
        this.dialog.open(ModifyApartmentDialogComponent,
            {
                panelClass: 'modify-apartment-dialog',
                data: {apartment: apartment}
            })
            .afterClosed().subscribe(isNeedToUpdatePage => isNeedToUpdatePage ? this.initPage() : EMPTY);
    }

    private initPage(): void {
        this.apartmentService.getPage$().subscribe(page => this.apartments = page.items);
    }

}
