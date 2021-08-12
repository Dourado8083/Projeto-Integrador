import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/model/Message';
import { MessageProfileDTO } from 'src/app/model/MessageProfileDTO';
import { Profile } from 'src/app/model/Profile';
import { MessageService } from 'src/app/service/message.service';
import { ProfileService } from 'src/app/service/profile.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  profile: Profile = new Profile();
  profileId: number = environment.id;
  profileToId: number = this.activatedRoute.snapshot.params["id"];

  messageProfileDto: MessageProfileDTO = new MessageProfileDTO();

  message: Message = new Message();
  messageList: Message[];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.getProfileById();
    this.getAllMessage();
  }

  getProfileById() {
    this.profileService.getProfileById(this.profileToId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getFeed() {
    this.profileService.getProfileById(this.profileToId).subscribe((resp: Profile) => {
      this.profile = resp;
      this.messageList = this.profile.messagesReceived;
    });
  }

  getAllMessage() {
    this.messageService.getAllMessage().subscribe((resp: Message[]) => {
      this.messageList = resp;
    });
  }

  postMessage() {
    this.messageProfileDto.profileFromId = this.profileId;
    this.messageProfileDto.profileToId = this.profileToId;
    this.messageService.postMessage(this.messageProfileDto).subscribe((resp: Message) => {
      this.getAllMessage();
    });
  }


}
