import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";
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

    constructor(private dialog: MatDialogRef<ModifyApartmentDialogComponent>,
                private fb: FormBuilder,
                private apartmentService: ApartmentService) {
    }

    ngOnInit(): void {
        this.initForm();
    }

    close(): void {
        this.dialog.close(false);
    }

    save(): void {
        const formValue = this.apartmentForm.value;
        this.apartmentService.create$(formValue).subscribe(resp => this.dialog.close(true));
    }

    private initForm(): void {
        this.apartmentForm = this.fb.group({
            name: this.fb.control(this.apartment?.name || '', [Validators.required]),
            apartmentType: this.fb.control(this.apartment?.apartmentType || null, [Validators.required]),
            description: this.fb.control(this.apartment?.description || "", [Validators.required]),
            geoData: this.fb.control(this.apartment?.geoData || '', [Validators.required])
        });
    }

}
