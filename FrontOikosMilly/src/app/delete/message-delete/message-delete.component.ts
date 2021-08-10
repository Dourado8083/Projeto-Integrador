import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/model/Message';
import { MessageService } from 'src/app/service/message.service';
import { environment } from 'src/environments/environment.prod';
@Component({
  selector: 'app-message-delete',
  templateUrl: './message-delete.component.html',
  styleUrls: ['./message-delete.component.css']
})
export class MessageDeleteComponent implements OnInit {

  idMessage: number
  message: Message = new Message()
 
 

  constructor(
    private messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(){
    if(environment.token ==''){
      this.router.navigate(['/entrar'])
     }
     this.idMessage = this.route.snapshot.params['id']
     this.findByIdMessage(this.idMessage)
    }
  
  
  findByIdMessage(messageId: number){
    this.messageService.getByIdMessage(this.idMessage).subscribe((resp: Message)=>{
      resp = this.message
    })
  }

  apagar(){
    this.messageService.deleteMessage(this.idMessage).subscribe(()=>{
    alert('mensagem apagada!')  
    this.router.navigate(['/menssage-content'])
    })
  }
}