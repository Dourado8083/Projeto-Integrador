import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { Profile } from '../model/Profile';
import { ProfileCommunityDTO } from '../model/ProfileCommunityDTO';
import { CommunityService } from '../service/community.service';
import { ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrls: ['./community.component.css']
})
export class CommunityComponent implements OnInit {

  profile: Profile = new Profile();
  profileId: number = environment.id;

  memberType: number;

  profileCommunityDto: ProfileCommunityDTO = new ProfileCommunityDTO();

  community: Community = new Community();
  communityList: Community[];


  constructor(
    private router: Router,
    private communityService: CommunityService,
    private profileService: ProfileService
  ) { }

  ngOnInit() {

    window.scroll(0, 0);

    this.getAllCommunities();
    this.getProfileById();
  }

  getAllCommunities() {
    this.communityService.getAll().subscribe((resp: Community[]) => {
      
      this.communityList = resp;

    });
  }

  createCommunity() {

    this.profileCommunityDto.profileId = environment.id;
    this.profileCommunityDto.profileEmail = environment.email;

    this.communityService.createCommunity(this.profileCommunityDto).subscribe(() => {
      alert("Comunidade criada com sucesso!");
      this.getAllCommunities();
      this.getProfileById();
    })
  }

  getCommunityById(communityId: number) {
    this.communityService.getCommunityById(communityId).subscribe((resp: Community) => {
      this.community = resp;
    });
  }

  getProfileById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

}
