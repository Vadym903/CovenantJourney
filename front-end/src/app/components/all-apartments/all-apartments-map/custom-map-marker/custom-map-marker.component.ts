import { Component, Input } from '@angular/core';
import { Apartment } from "../../../../_models/apartment.model";

@Component({
	selector: 'app-custom-map-marker',
	templateUrl: './custom-map-marker.component.html',
	styleUrls: ['./custom-map-marker.component.scss']
})
export class CustomMapMarkerComponent {

	@Input() set apartment(apartment: Apartment) {
		this._apartment = apartment;
		console.log(apartment);
	}

	_apartment: Apartment;

	constructor() {
	}

}
