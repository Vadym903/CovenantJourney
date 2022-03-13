import { environment } from 'src/environments/environment';

export class User {
    public id?: number;
    public firstName?: string;
    public secondName?: string;
    public login?: string;
    public password?: string;
    public email?: string;
    public role?: string;
    public photoId?: number;
    public photoUrl?: string;
    public description?: string;

    constructor(init?: Partial<User>) {
        Object.assign(this, init);
    }

    static getPhotoUrl(user: User): string {
        const url = environment.serverUrl + `photos/${user.photoId}`;
        return user.photoId ? url : 'assets\\img\\user-def.png';
    }
}
