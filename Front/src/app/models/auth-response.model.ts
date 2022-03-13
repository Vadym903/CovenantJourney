import { User } from "./user.model";

export class AuthResponse {
    public user: User;
    public token: string;
}