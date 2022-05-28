import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { Accommodation } from "../../../../_models/_enums/accommodation.enum";

@Component({
	selector: 'app-modify-accommodations',
	templateUrl: './modify-accommodations.component.html',
	styleUrls: ['./modify-accommodations.component.scss']
})
export class ModifyAccommodationsComponent {

	@Input() set parentForm(form: FormGroup) {
		this.accommodationsControl = form.get('accommodations') as FormControl;
	}

	accommodationsControl: FormControl;
	accommodations: string[] = Object.keys(Accommodation);

	onCheckboxChange(isChecked: boolean, accommodation: string): void {
		if (isChecked) {
			this.accommodationsControl.value.push(accommodation);
		} else {
			const index = this.accommodationsControl.value.findIndex(item => item === accommodation);
			this.accommodationsControl.value.splice(index, 1);
		}
	}

	isSelected(accommodation: string): boolean {
		return this.accommodationsControl.value.includes(accommodation);
	}

}
