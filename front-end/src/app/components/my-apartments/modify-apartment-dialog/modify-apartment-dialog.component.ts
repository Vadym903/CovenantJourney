import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Apartment } from "../../../_models/apartment.model";
import { ApartmentService } from "../../../services/apartment.service";

@Component({
	selector: 'app-modify-apartment-dialog',
	templateUrl: './modify-apartment-dialog.component.html',
	styleUrls: ['./modify-apartment-dialog.component.scss']
})
export class ModifyApartmentDialogComponent implements OnInit {

	apartmentForm: FormGroup;
	apartment: Apartment;

	constructor(@Inject(MAT_DIALOG_DATA) public data: any,
				private dialog: MatDialogRef<ModifyApartmentDialogComponent>,
				private fb: FormBuilder,
				private apartmentService: ApartmentService) {
	}

	ngOnInit(): void {
		this.initForm(this.data.apartment);
	}

	close(): void {
		this.dialog.close(false);
	}

	save(): void {
		const formValue = this.apartmentForm.value;
		const images = formValue.images;

		this.apartmentService.create$(formValue).subscribe(resp => {
			if (images.length > 0) {
				this.apartmentService.createImages(resp.id, images).subscribe(() => this.dialog.close(true));
			}
			this.dialog.close(true);
		});
	}

	private initForm(apartment: Apartment): void {
		this.apartmentForm = this.fb.group({
			name: this.fb.control(apartment?.name || '', [Validators.required]),
			apartmentType: this.fb.control(apartment?.apartmentType || null, [Validators.required]),
			description: this.fb.control(apartment?.description || "", [Validators.required]),
			geoData: this.fb.control(apartment?.geoData || '', [Validators.required]),
			accommodations: this.fb.control(apartment?.accommodations || []),
			images: [[]]
		});
	}

}
