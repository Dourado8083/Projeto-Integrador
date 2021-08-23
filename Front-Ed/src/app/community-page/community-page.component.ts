import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { Message } from '../model/Message';
import { MessageCommunityDTO } from '../model/MessageCommunityDTO';
import { Profile } from '../model/Profile';
import { ProfileCommunityDTO } from '../model/ProfileCommunityDTO';
import { Threads } from '../model/Threads';
import { ThreadsDTO } from '../model/ThreadsDTO';
import { CommunityService } from '../service/community.service';
import { MessageService } from '../service/message.service';
import { ProfileService } from '../service/profile.service';
import { ThreadsService } from '../service/threads.service';

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

  threadsDto: ThreadsDTO = new ThreadsDTO();
  threads: Threads = new Threads();

  isMember: boolean;

  constructor(
    private communityService: CommunityService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private threadsService: ThreadsService,
    private router: Router
  ) { }

  ngOnInit() {

    /*
    if(environment.token == "") {
      alert("Sua seção expirou, faça login novamente.");
      this.router.navigate(["/entrar"]);
    }*/

    this.getCommunityById();
    this.getUserById();
    this.isMember = this.checkMembership();
  }

  getUserById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getCommunityById() {
    this.communityService.getCommunityById(this.communityId).subscribe((resp: Community) => {
      this.community = resp;
      console.log(this.community)
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
      this.getCommunityById();
      this.message = new Message();
    })
  }

  checkMembership(): boolean {
    for (let i = 0; i < this.profile.memberOf.length; i++) {
      if (this.profile.memberOf[i].communityId == this.communityId) {
        return true;
      }
    }

    return false;
  }

  createThreads() {
    this.threadsDto.threadsCreatorId = this.profileId;
    this.threadsDto.communityOnId = this.communityId;
    this.threadsService.createThreads(this.threadsDto).subscribe((resp: Threads) => {
      this.threads = resp;
      this.getCommunityById();
      this.threads = new Threads();
    })
  }


}
