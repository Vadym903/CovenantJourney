import { Injectable } from "@angular/core";
import { AbstractService } from "./abstract.service";
import { Apartment } from "../_models/apartment.model";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

@Injectable({providedIn: 'root'})
export class ApartmentService extends AbstractService<Apartment> {

    getURL(): string {
        return "apartments";
    }

    fromObject(object: Apartment): Apartment {
        return Apartment.fromObject(object);
    }

    createImages(apartmentId: number, images: File[]): Observable<Apartment> {
        let fd = new FormData();
        images.forEach(image => fd.append('files', image))

        return this.http.post<Apartment>(this.apiPath + `/images/${apartmentId}`, fd)
            .pipe(map(item => this.fromObject(item)));
    }

}
