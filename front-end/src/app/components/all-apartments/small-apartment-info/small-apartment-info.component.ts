import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { Router } from "@angular/router";

@Component({
	selector: 'app-small-apartment-info',
	templateUrl: './small-apartment-info.component.html',
	styleUrls: ['./small-apartment-info.component.scss']
})
export class SmallApartmentInfoComponent implements OnInit {

	@Input() set _apartment(apartment: Apartment) {
		this.apartment = apartment;
	};

	@Input() isNeedToActiveArrows = true;
	apartment: Apartment;

	constructor(private router: Router) {
	}

	ngOnInit(): void {
	}

	openInfoPage() {
		this.router.navigateByUrl(`info/${this.apartment.id}`);
	}

}
