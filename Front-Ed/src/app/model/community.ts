import { Message } from "./Message";
import { Profile } from "./Profile"
import { Threads } from "./Threads";

export class Community{

    public communityId: number;
    public communityName: string;
    public communityNumberOfMembers:number;
    public communityPic: string;
    public communityBio: string;
    public communityHeader: string;
    public communityOwner: Profile;
    public communityMembers: Profile[];
    public messages: Message[];
    public threadsList: Threads[];

}