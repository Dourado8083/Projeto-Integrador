import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/model/Business';
import { MessageBusinessDTO } from 'src/app/model/MessageBusinessDTO';
import { ProfileBusinessDTO } from 'src/app/model/ProfileBusinessDTO';
import { BusinessService } from 'src/app/service/business.service';

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
    private businessService: BusinessService
  ) { }

  ngOnInit() {
  }

  postMessage() {

  }

}
