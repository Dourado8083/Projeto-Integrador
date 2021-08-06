import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { CommunityService } from '../service/community.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {
  community: Community = new Community
  communityList: Community[]

  constructor(
    private router: Router,
    private communityService: CommunityService
  ) { }

  ngOnInit(){
    if(environment.token==''){
      this.router.navigate(['/entrar'])
    }
    this.findAllCommunity()
  }

  findAllCommunity(){
    this.communityService.getAllCommunity().subscribe((resp: Community[])=>{
      this.communityList=resp
    })
  }

  send(){
    this.communityService.postCommunity(this.community).subscribe((resp)=>{
      this.community = resp
      alert("Comunidade cadastrada.")
      this.findAllCommunity()
      this.community = new Community()
    })
  }

}
