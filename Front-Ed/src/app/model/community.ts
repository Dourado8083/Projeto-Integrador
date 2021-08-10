import { Message } from "./Message";
import { Profile } from "./Profile"

export class Community{

    public communityId: number;
    public communityName: string;
    public communityNumberOfMembers:number;
    public communityPic: string;
    public communityBio: string;
    public communityOwner: Profile;
    public communityMembers: Profile[];
    public messages: Message[];

}