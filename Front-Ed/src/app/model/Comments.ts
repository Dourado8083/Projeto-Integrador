import { Message } from "./Message";
import { Profile } from "./Profile";

export class Comments {
    
    public commentId: number;
    public commentContent: string;
    public profileFrom: Profile;
    public messageOn: Message;
    public data: Date;

}