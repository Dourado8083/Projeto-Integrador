import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Community } from 'src/app/model/community';
import { CommunityService } from 'src/app/service/community.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-delete-community',
  templateUrl: './delete-community.component.html',
  styleUrls: ['./delete-community.component.css']
})
export class DeleteCommunityComponent implements OnInit {

  private community: Community
  communityId: number

  constructor(
    private communityService: CommunityService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(){
    if(environment.token ==''){
      this.router.navigate(['/entrar'])
     }
     this.communityId = this.route.snapshot.params['id']
     this.findByCommunityId(this.communityId)
    }

  apagar(){
    this.communityService.deleteCommunity(this.communityId).subscribe(()=>{
      alert('Comunidade apagada!')
      this.router.navigate(['/community'])
    })
  }

  findByCommunityId(communityId: number){
    this.communityService.getCommunityById(this.communityId).subscribe((resp: Community)=>{
      resp = this.community
    })
  }

}
