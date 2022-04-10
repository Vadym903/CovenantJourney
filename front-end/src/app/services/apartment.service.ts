import { Injectable } from "@angular/core";
import { AbstractService } from "./abstract.service";
import { Apartment } from "../_models/apartment.model";

@Injectable({providedIn: 'root'})
export class ApartmentService extends AbstractService<Apartment> {

    getURL(): string {
        return "apartments";
    }

    fromObject(object: Apartment): Apartment {
        return Apartment.fromObject(object);
    }

}
