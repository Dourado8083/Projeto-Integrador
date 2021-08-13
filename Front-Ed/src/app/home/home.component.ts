import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Message } from '../model/Message';
import { MessageProfileDTO } from '../model/MessageProfileDTO';
import { Profile } from '../model/Profile';
import { MessageService } from '../service/message.service';
import { ProfileService } from '../service/profile.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  profile: Profile = new Profile();
  profileId: number = environment.id;

  messageProfileDto: MessageProfileDTO = new MessageProfileDTO();

  message: Message = new Message();
  messageList: Message[];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private profileService: ProfileService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.getProfileById();
    this.getAllMessage();
  }

  getProfileById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getFeed() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
      alert(this.profile.profileId)
      alert(this.profile.profileName)
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
    this.messageProfileDto.profileToId = this.profileId;
    this.messageService.postMessage(this.messageProfileDto).subscribe((resp: Message) => {
      this.getAllMessage();
    });
  }

}
