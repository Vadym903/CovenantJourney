import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { ApartmentType } from "../../../../_models/_enums/apartment-type.enum";

@Component({
    selector: 'app-apartment-form',
    templateUrl: './apartment-form.component.html',
    styleUrls: ['./apartment-form.component.scss']
})
export class ApartmentFormComponent implements OnInit {

    @Input() apartmentForm: FormGroup;
    typesToSelect = Object.keys(ApartmentType);

    constructor() {
    }

    ngOnInit(): void {
    }

}
