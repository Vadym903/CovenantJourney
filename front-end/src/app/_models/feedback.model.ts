import { AbstractModel } from "./abstract-model.model";
import { Apartment } from "./apartment.model";
import { User } from "./user.model";

export class Feedback extends AbstractModel {

	constructor(public override id?: number,
				public mark?: number,
				public description?: string,
				public apartment?: Apartment,
				public user?: User,
				public apartmentId?: number) {
		super(id);
	}

	static fromObject(object: Feedback): Feedback {
		return new Feedback(
			object.id,
			object.mark,
			object.description,
			Apartment.fromObject(object.apartment),
			User.fromObject(object.user),
			object.apartmentId
		);
	}

	fromObject(object: Feedback): Feedback {
		return Feedback.fromObject(object);
	}
}
