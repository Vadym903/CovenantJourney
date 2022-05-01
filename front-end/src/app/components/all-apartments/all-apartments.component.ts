import { Component, OnInit } from '@angular/core';
import { Apartment } from "../../_models/apartment.model";
import { ApartmentService } from "../../services/apartment.service";
import { MatDialog } from "@angular/material/dialog";
import { AllApartmentsMapComponent } from "./all-apartments-map/all-apartments-map.component";

@Component({
    selector: 'app-all-apartments',
    templateUrl: './all-apartments.component.html',
    styleUrls: ['./all-apartments.component.scss']
})
export class AllApartmentsComponent implements OnInit {

    apartments: Apartment[] = [];

    constructor(private apartmentService: ApartmentService, private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.apartmentService.getPage$()
            .subscribe(page => this.apartments = page.items);
    }

    openMap() {
        this.dialog.open(AllApartmentsMapComponent, {panelClass: 'all-apartments-map-dialog'});
    }

}
