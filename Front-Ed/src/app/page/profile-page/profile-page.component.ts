import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/model/Message';
import { MessageProfileDTO } from 'src/app/model/MessageProfileDTO';
import { Profile } from 'src/app/model/Profile';
import { ProfileDTO } from 'src/app/model/ProfileDTO';
import { MessageService } from 'src/app/service/message.service';
import { ProfileService } from 'src/app/service/profile.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  myProfile: Profile = new Profile();
  visitingProfile: Profile = new Profile();
  myProfileId: number = environment.id;
  visitingProfileId: number = this.activatedRoute.snapshot.params["id"];

  messageProfileDto: MessageProfileDTO = new MessageProfileDTO();

  profDTO: ProfileDTO = new ProfileDTO();

  message: Message = new Message();
  messageList: Message[];

  constructor(
    private router: Router,
    private messageService: MessageService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {

    window.scroll(0,0)
    
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.getMyProfileById();
    this.getVisitingProfileById();
  }

  getMyProfileById() {
    this.profileService.getProfileById(this.myProfileId).subscribe((resp: Profile) => {
      this.myProfile = resp;
    });
  }

  getVisitingProfileById() {
    this.profileService.getProfileById(this.visitingProfileId).subscribe((resp: Profile) => {
      this.visitingProfile = resp;
      console.log(this.visitingProfile)
    });  
  }

  postMessage() {
    this.messageProfileDto.profileFromId = this.myProfileId;
    this.messageProfileDto.profileToId = this.visitingProfileId;
    this.messageProfileDto.messageType = "profile";
    this.messageService.postMessage(this.messageProfileDto).subscribe((resp: Message) => {
      this.getVisitingProfileById();
    });
  }

  updateBio(){
    this.profDTO.profileId = this.visitingProfileId

    this.profileService.editBio(this.profDTO).subscribe((resp: Profile)=>{
      this.visitingProfile = resp
      this.getVisitingProfileById()
      alert('Biografia atualizada')
    })
  }

  updateHeader(){
    this.profDTO.profileId = this.visitingProfileId

    this.profileService.editHeader(this.profDTO).subscribe((resp: Profile)=>{
      this.visitingProfile = resp
      this.getVisitingProfileById()
      alert('Header atualizada')
    })
  }

}