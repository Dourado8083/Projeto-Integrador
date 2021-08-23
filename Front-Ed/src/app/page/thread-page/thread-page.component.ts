import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageDTO } from 'src/app/model/MessageDTO';
import { Threads } from 'src/app/model/Threads';
import { ThreadsDTO } from 'src/app/model/ThreadsDTO';
import { MessageService } from 'src/app/service/message.service';
import { ThreadsService } from 'src/app/service/threads.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-thread-page',
  templateUrl: './thread-page.component.html',
  styleUrls: ['./thread-page.component.css']
})
export class ThreadPageComponent implements OnInit {
threadsid:number
threadDTO:ThreadsDTO = new ThreadsDTO()
thread:Threads = new Threads()
messageDTO: MessageDTO = new MessageDTO()
  constructor( private router:Router,
  private http:HttpClient,
  private activated:ActivatedRoute,
  private threadService:ThreadsService,
  private messageService:MessageService

  ) { }

  ngOnInit() {
    
    window.scroll(0,0)
    
    if (environment.token == '') {
      this.router.navigate(['/entrar'])
    }
  }
getThreadsById(){
  this.threadService.getThreadsById(this.threadsid).subscribe((resp:Threads)=>{
  this.thread = resp
  })
}
/*
createmessage(){
this.
}
*/
}
