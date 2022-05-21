import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Apartment } from "../../_models/apartment.model";
import { ApartmentService } from "../../services/apartment.service";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
	selector: 'app-apartment-info',
	templateUrl: './apartment-info.component.html',
	styleUrls: ['./apartment-info.component.scss']
})
export class ApartmentInfoComponent implements OnInit {

	apartment: Apartment;
	apartmentDescriptionHtml;
	userDescriptionHtml;

	constructor(private activatedRoute: ActivatedRoute,
				private apartmentService: ApartmentService,
				private sanitizer: DomSanitizer) {
	}

	ngOnInit(): void {
		const apartmentId = +this.activatedRoute.snapshot.paramMap.get('id');
		this.apartmentService.getById$(apartmentId).subscribe(apartment => {
			this.apartment = apartment;
			this.apartmentDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(apartment?.description);
			this.userDescriptionHtml = this.sanitizer.bypassSecurityTrustHtml(apartment?.user.description);
		});
	}

}
