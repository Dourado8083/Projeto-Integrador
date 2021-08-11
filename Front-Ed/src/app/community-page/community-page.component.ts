import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { Profile } from '../model/Profile';
import { AuthService } from '../service/auth.service';
import { CommunityService } from '../service/community.service';
import { ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-community-page',
  templateUrl: './community-page.component.html',
  styleUrls: ['./community-page.component.css']
})
export class CommunityPageComponent implements OnInit {

  community: Community = new Community();
  communityId: number = this.activatedRoute.snapshot.params["id"];

  profile: Profile = new Profile();
  profileId: number = environment.id;

  constructor(
    private communityService: CommunityService,
    private profileService: ProfileService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.getUserById();
    this.getCommunityById();
  }

  getUserById() {
    this.profileService.getProfileById(this.profileId).subscribe((resp: Profile) => {
      this.profile = resp;
    });
  }

  getCommunityById() {
    this.communityService.getCommunityById(this.communityId).subscribe((resp: Community) => {
      this.community = resp;
    })
  }

}
