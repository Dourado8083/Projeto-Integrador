import { Community } from "./community";

export class Profile {
    public profileId: number;
    public profileName: string;
    public profileAlias: string;
    public profileEmail: string;
    public profilePassword: string;
    public profileBio: string;
    public profilePic: string;
    public memberOf: Community[];
}
