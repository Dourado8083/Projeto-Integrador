import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'src/app/model/Message';
import { MessageDTO } from 'src/app/model/MessageDTO';
import { Profile } from 'src/app/model/Profile';
import { Threads } from 'src/app/model/Threads';
import { ThreadsDTO } from 'src/app/model/ThreadsDTO';
import { MessageService } from 'src/app/service/message.service';
import { ProfileService } from 'src/app/service/profile.service';
import { ThreadsService } from 'src/app/service/threads.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-thread-page',
  templateUrl: './thread-page.component.html',
  styleUrls: ['./thread-page.component.css']
})
export class ThreadPageComponent implements OnInit {

  profile: Profile = new Profile();
  profileId: number = environment.id;

  threadsId: number = this.activated.snapshot.params["id"];
  threadDTO: ThreadsDTO = new ThreadsDTO();
  thread: Threads = new Threads();

  messageDto: MessageDTO = new MessageDTO();
  message: Message = new Message();

  constructor(private router: Router,
    private http: HttpClient,
    private activated: ActivatedRoute,
    private threadService: ThreadsService,
    private messageService: MessageService,
    private profileService: ProfileService
  ) { }

  ngOnInit() {

    window.scroll(0, 0)

    this.getThreadsById();
    this.getProfileById();
  }


  getProfileById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
      console.log(this.profile)
    });
  }

  getThreadsById() {
    this.threadService.getThreadsById(this.threadsId).subscribe((resp: Threads) => {
      this.thread = resp;
      console.log(this.thread);
    })
  }

  postMessage() {
    this.messageDto.profileFromId = this.profileId;
    this.messageDto.threadsOnId = this.threadsId;
    this.messageService.postMessageOnThreads(this.messageDto).subscribe((resp: Message) => {
      this.message = resp;
      this.message = new Message();
      this.getThreadsById();
    })
  }


}
