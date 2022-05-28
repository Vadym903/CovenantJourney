import { AbstractModel } from "./abstract-model.model";
import { Apartment } from "./apartment.model";
import { User } from "./user.model";

export class Feedback extends AbstractModel {

	constructor(public override id?: number,
				public cleanlinessMark?: number,
				public locationMark?: number,
				public communicationMark?: number,
				public serviceMark?: number,
				public description?: string,
				public apartment?: Apartment,
				public user?: User,
				public apartmentId?: number,
				public descriptionHtml?: any) {
		super(id);
	}

	static fromObject(object: Feedback): Feedback {
		return new Feedback(
			object.id,
			object.cleanlinessMark,
			object.locationMark,
			object.communicationMark,
			object.serviceMark,
			object.description,
			object.apartment ? Apartment.fromObject(object.apartment) : undefined,
			object.user ? User.fromObject(object.user) : undefined,
			object.apartmentId
		);
	}

	fromObject(object: Feedback): Feedback {
		return Feedback.fromObject(object);
	}
}
