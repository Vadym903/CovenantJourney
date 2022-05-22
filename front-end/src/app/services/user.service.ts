import { AbstractService } from "./abstract.service";
import { Injectable } from "@angular/core";
import { User } from "../_models/user.model";

@Injectable({providedIn: 'root'})
export class UserService extends AbstractService<User> {

	getURL(): string {
		return "users";
	}

	fromObject(object: User): User {
		return User.fromObject(object);
	}
}
