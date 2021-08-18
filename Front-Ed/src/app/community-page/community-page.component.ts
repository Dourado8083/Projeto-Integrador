import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { Message } from '../model/Message';
import { MessageCommunityDTO } from '../model/MessageCommunityDTO';
import { Profile } from '../model/Profile';
import { ProfileCommunityDTO } from '../model/ProfileCommunityDTO';
import { AuthService } from '../service/auth.service';
import { CommunityService } from '../service/community.service';
import { MessageService } from '../service/message.service';
import { ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-community-page',
  templateUrl: './community-page.component.html',
  styleUrls: ['./community-page.component.css']
})

export class CommunityPageComponent implements OnInit {

  community: Community = new Community();
  communityId: number = this.activatedRoute.snapshot.params["id"];

  message: Message = new Message();
  messageList: Message[];

  messageCommunityDto: MessageCommunityDTO = new MessageCommunityDTO();

  profile: Profile = new Profile();
  profileId: number = environment.id;

  profileCommunityDto: ProfileCommunityDTO = new ProfileCommunityDTO();

  constructor(
    private communityService: CommunityService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private router: Router
  ) { }

  ngOnInit() {
    
    if(environment.token == "") {
      alert("Sua seção expirou, faça login novamente.");
      this.router.navigate(["/entrar"]);
    }

    this.getCommunityById();
    this.getUserById();
  }

  getUserById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getCommunityById() {
    this.communityService.getCommunityById(this.communityId).subscribe((resp: Community) => {
      this.community = resp;
      console.log(this.community.messages)
    })
  }

  editCommunityBio() {
    this.profileCommunityDto.communityId = this.communityId;
    this.profileCommunityDto.profileId = this.profileId;
    this.communityService.editCommunityBio(this.profileCommunityDto).subscribe((resp: Community) => {
      alert("Biografia alterada com sucesso!");
      this.getCommunityById();
    });
    this.profileCommunityDto = new ProfileCommunityDTO();
  }

  joinCommunity() {
    this.profileCommunityDto.communityId = this.communityId;
    this.profileCommunityDto.profileId = this.profileId;
    this.communityService.joinCommunity(this.profileCommunityDto).subscribe((resp: Community) => {
      this.community = resp;
      this.getCommunityById();
      this.getUserById();
    }); 
    this.profileCommunityDto = new ProfileCommunityDTO();
  }

  leaveCommunity() {
    this.profileCommunityDto.communityId = this.communityId;
    this.profileCommunityDto.profileId = this.profileId;
    this.communityService.leaveCommunity(this.profileCommunityDto).subscribe((resp: Community) => {
      this.community = resp;
      this.getCommunityById();
      this.getUserById();
    }); 
    this.profileCommunityDto = new ProfileCommunityDTO();
  }

  editCommunityPic() {

  }

  deleteCommunity() {
    this.communityService.deleteCommunity(this.communityId).subscribe(() => {
      alert("Comunida de deletada com sucesso!");
      this.router.navigate(["/community"]);
    });

  }

  postMessage() {
    this.messageCommunityDto.profileFromId = this.profileId;
    this.messageCommunityDto.communityToId = this.communityId;
    this.messageCommunityDto.messageType = "community";
    this.messageService.postMessageOnCommunity(this.messageCommunityDto).subscribe((resp: Message) => {
      this.message = resp;
    })
  }

  isMember(): boolean {
    for(let i = 0; i < this.profile.memberOf.length; i++) {
      if(this.profile.memberOf[i].communityId == this.communityId) {
        return true;
      }
    }

    return false;
  }

}
