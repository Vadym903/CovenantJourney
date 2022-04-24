import { AbstractModel } from "./abstract-model.model";
import { ApartmentType } from "./_enums/apartment-type.enum";
import { GeoData } from "./geo-data.model";

export class Apartment extends AbstractModel {

    constructor(public override id: number,
                public name?: string,
                public apartmentType?: ApartmentType,
                public geoData?: GeoData,
                public description?: string) {
        super(id);
    }

    static fromObject(object: Apartment): Apartment {
        return new Apartment(
            object.id,
            object.name,
            object.apartmentType,
            GeoData.fromObject(object.geoData),
            object.description
        );
    }

    fromObject<T extends AbstractModel>(object: T): T {
        return undefined;
    }


}
