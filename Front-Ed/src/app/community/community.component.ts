import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { Profile } from '../model/Profile';
import { ProfileCommunityDTO } from '../model/ProfileCommunityDTO';
import { CommunityService } from '../service/community.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {

  profile: Profile = new Profile();

  profileCommunityDto: ProfileCommunityDTO = new ProfileCommunityDTO();

  community: Community = new Community();
  communityList: Community[];

  constructor(
    private communityService: CommunityService
  ) { }

  ngOnInit() {
    this.getAllCommunity();
  }

  getAllCommunity() {
    this.communityService.getAll().subscribe((resp: Community[]) => {
      this.communityList = resp; 
    });
  }
 
  createCommunity() {
    
    this.profileCommunityDto.profileId = environment.id;
    this.profileCommunityDto.profileEmail = environment.email;

    this.communityService.createCommunity(this.profileCommunityDto).subscribe(() => {
      alert("Comunidade criada com sucesso!");
      this.getAllCommunity();
    })
  }

}
