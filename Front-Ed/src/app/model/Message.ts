import { Business } from "./Business";
import { Community } from "./community"
import { Profile } from "./Profile"

export class Message{
    
    public messageId: number;
    public messageTitle: string;
    public messageContent: string;
    public messagePic: string;
    public communityOn: Community;
    public profileFrom: Profile;
    public profileOn: Profile;
    public businessOn: Business;

}