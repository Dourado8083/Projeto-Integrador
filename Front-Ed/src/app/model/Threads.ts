import { Community } from "./community";
import { Message } from "./Message";
import { Profile } from "./Profile";

export class Threads {

    public threadsId: number;
    public threadsTitle: string;
    public numberOfMessages: number;
    public threadsCreator: Profile;
    public communityOn: Community;
    public messageList: Message[];
    
}