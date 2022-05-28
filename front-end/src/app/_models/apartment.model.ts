import { AbstractModel } from "./abstract-model.model";
import { ApartmentType } from "./_enums/apartment-type.enum";
import { GeoData } from "./geo-data.model";
import { Image } from "./image.model";
import { User } from "./user.model";
import { Accommodation } from "./_enums/accommodation.enum";

export class Apartment extends AbstractModel {

	constructor(public override id?: number,
				public name?: string,
				public apartmentType?: ApartmentType,
				public geoData?: GeoData,
				public description?: string,
				public images?: Image[],
				public user?: User,
				public averageMark?: number,
				public countOfFeedbacks?: number,
				public accommodations?: Accommodation[],
	) {
		super(id);
	}

	static fromObject(object: Apartment): Apartment {
		return new Apartment(
			object.id,
			object.name,
			object.apartmentType,
			GeoData.fromObject(object.geoData),
			object.description,
			object.images?.map(image1 => Image.fromObject(image1)),
			User.fromObject(object.user),
			object.averageMark,
			object.countOfFeedbacks,
			object.accommodations
		);
	}

	fromObject<T extends AbstractModel>(object: T): T {
		return undefined;
	}

}
