import { AbstractService } from "./abstract.service";
import { Injectable } from "@angular/core";
import { Feedback } from "../_models/feedback.model";

@Injectable({providedIn: 'root'})
export class FeedbackService extends AbstractService<Feedback> {

	getURL(): string {
		return "feedbacks";
	}

	fromObject(object: Feedback): Feedback {
		return Feedback.fromObject(object);
	}
}
