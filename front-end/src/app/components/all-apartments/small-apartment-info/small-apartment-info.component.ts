import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";

@Component({
	selector: 'app-small-apartment-info',
	templateUrl: './small-apartment-info.component.html',
	styleUrls: ['./small-apartment-info.component.scss']
})
export class SmallApartmentInfoComponent implements OnInit {

	@Input() set _apartment(apartment: Apartment) {
		this.apartment = apartment;
	};

	apartment: Apartment;

	constructor() {
	}

	ngOnInit(): void {
	}

}
