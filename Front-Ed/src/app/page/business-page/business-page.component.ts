import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/model/Business';
import { Comments } from 'src/app/model/Comments';
import { CommentsDTO } from 'src/app/model/CommentsDTO';
import { Ecommerce } from 'src/app/model/Ecommerce';
import { EcommerceDTO } from 'src/app/model/EcommerceDTO';
import { Message } from 'src/app/model/Message';
import { MessageBusinessDTO } from 'src/app/model/MessageBusinessDTO';
import { Profile } from 'src/app/model/Profile';
import { ProfileBusinessDTO } from 'src/app/model/ProfileBusinessDTO';
import { BusinessService } from 'src/app/service/business.service';
import { CommentService } from 'src/app/service/comment.service';
import { EcommerceService } from 'src/app/service/ecommerce.service';
import { MessageService } from 'src/app/service/message.service';
import { ProfileService } from 'src/app/service/profile.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-business-page',
  templateUrl: './business-page.component.html',
  styleUrls: ['./business-page.component.css']
})
export class BusinessPageComponent implements OnInit {

  business: Business = new Business();
  businessId: number = this.activatedRoute.snapshot.params["id"];

  profile: Profile = new Profile();
  profileId: number = environment.id;

  ecommerce: Ecommerce = new Ecommerce();
  ecommerceDto: EcommerceDTO = new EcommerceDTO();

  comment: Comments;

  messageBusinessDto: MessageBusinessDTO = new MessageBusinessDTO();
  commentsDto: CommentsDTO = new CommentsDTO();

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private businessService: BusinessService,
    private profileService: ProfileService,
    private commentService: CommentService,
    private messageService: MessageService,
    private ecommerceService: EcommerceService
  ) { }

  ngOnInit() {
    this.getBusinessById();
    this.getProfileById();
  }

  postMessage() {
    this.messageBusinessDto.businessToId = this.businessId
    this.messageBusinessDto.messageType = "business";
    this.messageService.postMessageOnBusiness(this.messageBusinessDto).subscribe((resp: Message) => {
      this.getBusinessById();
      this.messageBusinessDto = new MessageBusinessDTO();
    });
  }

  postComment(messageOnId: number) {
    this.commentsDto.messageOnId = messageOnId;
    this.commentsDto.profileFromId = this.profileId;
    this.commentService.postComment(this.commentsDto).subscribe((resp: Comments) => {
      this.getBusinessById();
      this.commentsDto = new CommentsDTO();
    });
  }

  getProfileById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getBusinessById() {
    this.businessService.getBusinessById(this.businessId).subscribe((resp: Business) => {
      this.business = resp;
    });
  }

  createEcommerce() {
    this.ecommerceDto.businessOwnerId = this.profileId;
    this.ecommerceDto.businessOnId = this.businessId;
    this.ecommerceDto.ecommerceName = this.business.businessName;
    this.ecommerceDto.ecommercePic = this.business.businessPic;
    this.ecommerceDto.ecommerceHeader = this.business.businessHeader;
    this.ecommerceService.createEcommerce(this.ecommerceDto).subscribe((resp: Ecommerce) => {
      this.ecommerce = resp;
      this.getBusinessById();
    });
  }

}
