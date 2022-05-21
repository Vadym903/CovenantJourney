import { AbstractModel } from "./abstract-model.model";
import { UserRole } from "./_enums/user-role.enum";

export class User extends AbstractModel {

    constructor(public override id?: number,
                public fullName?: string,
                public login?: string,
                public email?: string,
                public role?: UserRole,
                public description?: string) {
        super(id);
    }

   static fromObject(object: User): User {
        return new User(
            object.id,
            object.fullName,
            object.login,
            object.email,
            object.role,
            object.description
        );
    }

    fromObject(object: User): User {
        return User.fromObject(object);
    }
}
