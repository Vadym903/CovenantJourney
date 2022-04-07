import { User } from "./user.model";

export class AuthResponse {
    constructor(
        public user: User,
        public token: string
    ) {
    }
}
