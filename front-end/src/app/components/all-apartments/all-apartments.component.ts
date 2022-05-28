import { Component, OnInit } from '@angular/core';
import { Apartment } from "../../_models/apartment.model";
import { ApartmentService } from "../../services/apartment.service";
import { MatDialog } from "@angular/material/dialog";
import { AllApartmentsMapComponent } from "./all-apartments-map/all-apartments-map.component";
import { Filter } from "../../_models/filter-model";
import { FilteringOperation } from "../../shared/constants/filtering-operations.constants";
import { Accommodation } from "../../_models/_enums/accommodation.enum";

@Component({
	selector: 'app-all-apartments',
	templateUrl: './all-apartments.component.html',
	styleUrls: ['./all-apartments.component.scss']
})
export class AllApartmentsComponent implements OnInit {

	apartments: Apartment[] = [];
	nameFilter = "";
	addressFilter = "";
	accommodations = Object.keys(Accommodation);
	activeAccommodations = [];

	constructor(private apartmentService: ApartmentService, private dialog: MatDialog) {
	}

	ngOnInit(): void {
		this.apartmentService.getPage$()
			.subscribe(page => this.apartments = page.items);
	}

	openMap() {
		this.dialog.open(AllApartmentsMapComponent, {panelClass: 'all-apartments-map-dialog'});
	}

	onChangeNameFilter(filterValue, fieldName: string): void {
		this[fieldName] = filterValue;
		this.initPage();
	}

	handleAccommodationClick(accommodation: string): void {
		const index = this.activeAccommodations.findIndex(accomm => accomm === accommodation);

		if (index !== -1) {
			this.activeAccommodations.splice(index, 1);
		} else {
			this.activeAccommodations.push(accommodation);
		}

		this.initPage();
	}

	private initPage(): void {
		const filters = [
			new Filter("address", FilteringOperation.CONTAIN, this.addressFilter),
			new Filter("name", FilteringOperation.CONTAIN, this.nameFilter)
		];

		this.activeAccommodations.forEach(accomm =>
			filters.push(new Filter("accommodations", FilteringOperation.CONTAIN, accomm)));

		this.apartmentService.getPage$(0, 200, null, filters)
			.subscribe(page => this.apartments = page.items);
	}

}
