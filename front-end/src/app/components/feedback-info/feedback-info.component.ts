import { Component, Input } from '@angular/core';
import { Feedback } from "../../_models/feedback.model";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
	selector: 'app-feedback-info',
	templateUrl: './feedback-info.component.html',
	styleUrls: ['./feedback-info.component.scss']
})
export class FeedbackInfoComponent {

	@Input() set feedback(feedback: Feedback) {
		this._feedback = feedback;
		this.descriptionHtml = this.sanitizer.bypassSecurityTrustHtml(feedback?.description);
	}

	_feedback: Feedback;
	descriptionHtml;

	ratingConfig = {
		max: 5,
	};
	constructor(private sanitizer: DomSanitizer) {
	}

}
