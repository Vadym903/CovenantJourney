import { Component, OnInit } from '@angular/core';
import { ApartmentService } from "../../services/apartment.service";
import { MatDialog } from "@angular/material/dialog";
import { ModifyApartmentDialogComponent } from "./modify-apartment-dialog/modify-apartment-dialog.component";
import { Apartment } from "../../_models/apartment.model";
import { EMPTY } from "rxjs";
import { Filter } from "../../_models/filter-model";
import { FilteringOperation } from "../../shared/constants/filtering-operations.constants";
import { AuthService } from "../../services/auth-service.service";

@Component({
    selector: 'app-apartments',
    templateUrl: './apartments.component.html',
    styleUrls: ['./apartments.component.scss']
})
export class ApartmentsComponent implements OnInit {

    apartments: Apartment[] = [];

    constructor(private apartmentService: ApartmentService,
                private authService: AuthService,
                private dialog: MatDialog) {
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
        const filter = new Filter("owner", FilteringOperation.EQUAL, this.authService.getCurrentUser().id + '');
        this.apartmentService.getPage$(0, 200, [], [filter])
            .subscribe(page => this.apartments = page.items);
    }

}
