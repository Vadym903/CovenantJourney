import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
    selector: 'app-single-apartment-card',
    templateUrl: './single-apartment-card.component.html',
    styleUrls: ['./single-apartment-card.component.scss']
})
export class SingleApartmentCardComponent implements OnInit {

    @Input() set _apartment(apartment: Apartment) {
        this.apartment = apartment;
        this.descriptionHtml = this.sanitizer.bypassSecurityTrustHtml(apartment?.description);
        if (apartment.images[0]) {
            this.mainImage = apartment.images[0].imageFullPath;
        }
    }

    mainImage;
    descriptionHtml;
    apartment: Apartment;

    constructor(private sanitizer: DomSanitizer) {
    }

    ngOnInit(): void {
    }

}
