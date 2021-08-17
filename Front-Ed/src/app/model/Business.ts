import { Message } from "./Message";
import { Profile } from "./Profile";

export class Business {
    
    public businessId: number;
    public businessName: string;
    public businessAlias: string;
    public businessEmail: string;
    public businessPassword: string;
    public businessBio: string;
    public businessPic: string;
    public businessOwner: Profile;
    public businessMessages: Message[];

}