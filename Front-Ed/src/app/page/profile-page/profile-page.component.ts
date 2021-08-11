import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/model/Message';
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

  message: Message = new Message;
  messageList: Message[];

  profile: Profile = new Profile();
  profileId = this.activatedRoute.snapshot.params["id"];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }
    this.findAllMessage();
    this.getProfileById();
  }

  getProfileById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  findAllMessage() {
    this.messageService.getAllMessage().subscribe((resp: Message[]) => {
      this.messageList = resp;
    });
  }

  send() {
    this.profile.profileId = environment.id;
    this.message.profileOn = this.profile;
    
    this.messageService.postMessage(this.message).subscribe((resp) => {
      resp = this.message;
      alert('Mensagem cadastrada.');
      this.findAllMessage();

      this.message = new Message();
    });
  }

}
