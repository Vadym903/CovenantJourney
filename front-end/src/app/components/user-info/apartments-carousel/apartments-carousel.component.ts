import { Component, Input } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { OwlOptions } from "ngx-owl-carousel-o";

@Component({
	selector: 'app-apartments-carousel',
	templateUrl: './apartments-carousel.component.html',
	styleUrls: ['./apartments-carousel.component.scss']
})
export class ApartmentsCarouselComponent {

	@Input() set apartments(apartments: Apartment[]) {
		this._apartments = apartments;
		this.customOptions.items = apartments.length > 3 ? 3 : apartments.length;
	}

	_apartments: Apartment[] = [];

	customOptions: OwlOptions = {
		loop: true,
		autoHeight: true,
		margin: 30,
		stagePadding: -20,
		dots: true,
		navSpeed: 700,
		navText: ['', ''],
		items: 1
	}

	constructor() {
	}

}
