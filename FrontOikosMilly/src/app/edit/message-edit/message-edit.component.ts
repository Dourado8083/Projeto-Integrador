import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Community } from 'src/app/model/community';
import { Message } from 'src/app/model/Message';
import { MessageService } from 'src/app/service/message.service';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-message-edit',
  templateUrl: './message-edit.component.html',
  styleUrls: ['./message-edit.component.css']
})
export class MessageEditComponent implements OnInit {
  
  id: number
  message: Message = new Message()
  community: Community = new Community()
  

  constructor(
    private messageService: MessageService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(){
    if(environment.token ==''){
      this.router.navigate(['/entrar'])
     }
     
     let idMessage = this.route.snapshot.params['id']
  
     this.findByIdMessage(idMessage)
    }
  
  
  findByIdMessage(idMessage: number){
    this.messageService.getByIdMessage(idMessage).subscribe((resp: Message)=>{
      this.message = resp
    })
  }

  edit(){

    // this.message.messageId = this.idMessage
    
   
    this.messageService.putMessage(this.message).subscribe((resp: Message)=>{
      this.message = resp
      alert('Tema atualizado!')
      alert(JSON.stringify(this.message))
      this.router.navigate(['/menssage-content'])
    })
  }

}
