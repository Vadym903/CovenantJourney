import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { FeedbackService } from "../../../services/feedback.service";
import { Feedback } from "../../../_models/feedback.model";
import { Filter } from "../../../_models/filter-model";
import { FilteringOperation } from "../../../shared/constants/filtering-operations.constants";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { finalize } from "rxjs";

@Component({
	selector: 'app-apartment-comments',
	templateUrl: './apartment-comments.component.html',
	styleUrls: ['./apartment-comments.component.scss']
})
export class ApartmentCommentsComponent implements OnInit {

	@Input() apartment: Apartment;
	feedbacks: Feedback[] = [];
	feedbackForm: FormGroup = null;
	ratingConfig = {
		value: 0,
		max: 5,
		color: "accent"
	};

	constructor(private feedbackService: FeedbackService, private fb: FormBuilder) {
	}

	ngOnInit(): void {
		this.initFeedbacks();
	}

	private initFeedbacks(): void {
		const filter = new Filter('apartment', FilteringOperation.EQUAL, this.apartment.id + '');
		this.feedbackService.getPage$(0, 100, [], [filter])
			.subscribe(page => this.feedbacks = page.items);
	}

	initFeedbackForm(): void {
		this.feedbackForm = this.fb.group({
			cleanlinessMark: this.fb.control(0, Validators.required),
			locationMark: this.fb.control(0, Validators.required),
			communicationMark: this.fb.control(0, Validators.required),
			serviceMark: this.fb.control(0, Validators.required),
			description: this.fb.control('', Validators.required)
		});
	}

	updateMarkField(markValue, fieldName): void {
		this.feedbackForm.get(fieldName).setValue(markValue);
	}

	removeFeedback(): void {
		this.feedbackForm = null;
	}

	saveFeedback(): void {
		const formValue = this.feedbackForm.value;
		const feedback = new Feedback();
		feedback.cleanlinessMark = formValue.cleanlinessMark;
		feedback.locationMark = formValue.locationMark;
		feedback.communicationMark = formValue.communicationMark;
		feedback.serviceMark = formValue.serviceMark;
		feedback.description = formValue.description;
		feedback.apartmentId = this.apartment.id;

		this.feedbackService.create$(feedback)
			.pipe(finalize(() => this.feedbackForm = null))
			.subscribe(() => this.initFeedbacks());
	}
}
