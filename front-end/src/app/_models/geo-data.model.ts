import { AbstractModel } from "./abstract-model.model";

export class GeoData extends AbstractModel {

    constructor(public override id: number,
                public latitude: number,
                public longitude: number,
                public altitude: number,
                public addressName: string
    ) {
        super(id);
    }

    static fromObject(object: GeoData): GeoData {
        return new GeoData(
            object.id,
            object.latitude,
            object.longitude,
            object.altitude,
            object.addressName
        );
    }

    fromObject(object: GeoData): GeoData {
        return GeoData.fromObject(object);
    }
}
