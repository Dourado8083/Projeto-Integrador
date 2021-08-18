import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Business } from '../model/Business';
import { Message } from '../model/Message';
import { MessageProfileDTO } from '../model/MessageProfileDTO';
import { Profile } from '../model/Profile';
import { ProfileBusinessDTO } from '../model/ProfileBusinessDTO';
import { BusinessService } from '../service/business.service';
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

  business: Business = new Business();

  messageProfileDto: MessageProfileDTO = new MessageProfileDTO();
  profileBusinessDto: ProfileBusinessDTO = new ProfileBusinessDTO();

  message: Message = new Message();
  messageList: Message[];
  idMessage: number

  constructor(
    private router: Router,
    private messageService: MessageService,
    private profileService: ProfileService,
    private businessService: BusinessService,
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

  getAllMessage() {
    this.messageService.getAllMessage().subscribe((resp: Message[]) => {
      this.messageList = resp;
    });
  }

  postMessage() {
    this.messageProfileDto.profileFromId = this.profileId;
    this.messageProfileDto.profileToId = this.profileId;
    this.messageProfileDto.messageType = "feed"
    this.messageService.postMessage(this.messageProfileDto).subscribe((resp: Message) => {
      this.getAllMessage();
    });
  }

  delete() {
    this.messageService.deleteMessage(this.idMessage).subscribe(() => {
      alert('Postagem apagada com sucesso!')
    })
  }

  createBusiness() {
    this.profileBusinessDto.profileId = this.profileId;
    this.businessService.createBusiness(this.profileBusinessDto).subscribe((resp: Business) => {
      this.getProfileById();
      alert("Seu negócio foi cadastrado com sucesso!")
    });
  }

}
