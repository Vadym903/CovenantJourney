import { Component, Input } from '@angular/core';
import { Feedback } from "../../../_models/feedback.model";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
	selector: 'app-user-info-feedbacks',
	templateUrl: './user-info-feedbacks.component.html',
	styleUrls: ['./user-info-feedbacks.component.scss']
})
export class UserInfoFeedbacksComponent {

	@Input() set feedbacks(feedbacks: Feedback[]) {
		this._feedbacks = feedbacks.map(item => {
			item.descriptionHtml = this.sanitizer.bypassSecurityTrustHtml(item?.description);
			return item;
		});
	}

	_feedbacks = [];

	constructor(private sanitizer: DomSanitizer) {
	}

}
