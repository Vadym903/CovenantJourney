import { Component, Input, OnInit } from '@angular/core';
import { Apartment } from "../../../_models/apartment.model";
import { FeedbackService } from "../../../services/feedback.service";
import { Feedback } from "../../../_models/feedback.model";
import { Filter } from "../../../_models/filter-model";
import { FilteringOperation } from "../../../shared/constants/filtering-operations.constants";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

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
			mark: this.fb.control('', Validators.required),
			description: this.fb.control('', Validators.required)
		});
	}
}
