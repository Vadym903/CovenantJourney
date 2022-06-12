import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { DomSanitizer } from "@angular/platform-browser";
import { ApartmentService } from "../../../services/apartment.service";

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

    @Output() editEmitter = new EventEmitter<Apartment>();

    mainImage;
    descriptionHtml;
    apartment: Apartment;

    constructor(private sanitizer: DomSanitizer, private apartmentService: ApartmentService) {
    }

    ngOnInit(): void {
    }

    edit(): void {
        this.editEmitter.emit(this.apartment);
    }

    delete(): void {
        this.apartmentService.delete$(this.apartment.id).subscribe();
    }

}
