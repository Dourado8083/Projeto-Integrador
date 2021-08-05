import { Component, OnInit } from '@angular/core';
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
    private messageService: MessageService
  ) { }

  ngOnInit() {
  }

  send()
  {
    console.log(environment.token)
    console.log(environment.name)
    console.log("bom dia")
    this.messageService.postMessage(this.message).subscribe((resp)=>{
      this.message = resp
      alert('Mensagem cadastrada.')
      this.message = new Message()
    })
  }

}
