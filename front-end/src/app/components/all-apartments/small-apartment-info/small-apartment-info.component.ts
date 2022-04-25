import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";

@Component({
    selector: 'app-small-apartment-info',
    templateUrl: './small-apartment-info.component.html',
    styleUrls: ['./small-apartment-info.component.scss']
})
export class SmallApartmentInfoComponent implements OnInit {

    @Input() apartment: Apartment;

    constructor() {
    }

    ngOnInit(): void {
    }

}
