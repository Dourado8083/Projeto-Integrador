import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Message } from '../model/Message';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-menssage-content',
  templateUrl: './menssage-content.component.html',
  styleUrls: ['./menssage-content.component.css']
})
export class MenssageContentComponent implements OnInit {
  mesagem:string = "";
  message: Message = new Message
  messageList: Message[]

  constructor(
    private router: Router,
    private messageService: MessageService
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
    console.log(environment.token)
    console.log(environment.name)
    this.messageService.postMessage(this.message).subscribe((resp)=>{
      this.message = resp
      alert('Mensagem cadastrada.')
      this.findAllMessage()

      this.message = new Message()
    })
  }

}
