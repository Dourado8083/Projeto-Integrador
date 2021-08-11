import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Message } from '../model/Message';
import { Profile } from '../model/Profile';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  

  mesagem:string = "";
  message: Message = new Message
  messageList: Message[]
  profile: Profile

  profilee: Profile = new Profile()
  profileId = environment.id

  constructor(
    private router: Router,
    private messageService: MessageService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if(environment.token==''){
      this.router.navigate(['/entrar'])
    }
    this.findAllMessage()
  }
  findAllMessage(){
    this.messageService.getAllMessage().subscribe((resp: Message[])=>{
      this.messageList=resp
    })
  }
  send()
  {
    this.profile.profileId = environment.id;
    this.message.profileOn = this.profile

    console.log(environment.token)
    console.log(environment.name)
    this.messageService.postMessage(this.message).subscribe((resp)=>{
        resp = this.message
      alert('Mensagem cadastrada.')
      this.findAllMessage()

      this.message = new Message()
    })
  }
 

}
