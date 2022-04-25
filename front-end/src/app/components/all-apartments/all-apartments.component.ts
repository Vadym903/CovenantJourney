import { Component, OnInit } from '@angular/core';
import { Apartment } from "../../_models/apartment.model";
import { ApartmentService } from "../../services/apartment.service";

@Component({
    selector: 'app-all-apartments',
    templateUrl: './all-apartments.component.html',
    styleUrls: ['./all-apartments.component.scss']
})
export class AllApartmentsComponent implements OnInit {

    apartments: Apartment[] = [];

    constructor(private apartmentService: ApartmentService) {
    }

    ngOnInit(): void {
        this.apartmentService.getPage$()
            .subscribe(page => this.apartments = page.items);
    }

}
