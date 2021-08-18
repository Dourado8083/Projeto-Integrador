import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/model/Business';
import { Message } from 'src/app/model/Message';
import { MessageBusinessDTO } from 'src/app/model/MessageBusinessDTO';
import { ProfileBusinessDTO } from 'src/app/model/ProfileBusinessDTO';
import { BusinessService } from 'src/app/service/business.service';
import { MessageService } from 'src/app/service/message.service';

@Component({
  selector: 'app-business-page',
  templateUrl: './business-page.component.html',
  styleUrls: ['./business-page.component.css']
})
export class BusinessPageComponent implements OnInit {

  business: Business = new Business();
  businessId: number = this.activatedRoute.snapshot.params["id"];

  messageBusinessDto: MessageBusinessDTO = new MessageBusinessDTO();


  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private businessService: BusinessService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.getBusinessById();

  }

  postMessage() {
    this.messageBusinessDto.businessToId = this.businessId

    this.messageService.postMessageOnBusiness(this.messageBusinessDto).subscribe((resp: Message)=>{
      this.getBusinessById()
    })
  }

  getBusinessById(){
    this.businessService.getBusinessById(this.businessId).subscribe((resp: Business)=>{
      this.business = resp
    })
  }

}
