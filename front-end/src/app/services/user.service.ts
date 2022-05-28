import { AbstractService } from "./abstract.service";
import { Injectable } from "@angular/core";
import { User } from "../_models/user.model";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";

@Injectable({providedIn: 'root'})
export class UserService extends AbstractService<User> {

	getURL(): string {
		return "users";
	}

	fromObject(object: User): User {
		return User.fromObject(object);
	}

	updateCurrentUser(entity: User): Observable<User> {
		return this.http.post<User>(this.apiPath + '/current', entity)
			.pipe(map(item => this.fromObject(item)));
	}
}
