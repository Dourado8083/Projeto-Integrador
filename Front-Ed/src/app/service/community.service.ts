import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';
import { ProfileCommunityDTO } from '../model/ProfileCommunityDTO';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {


  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getAll(): Observable<Community[]> {
    return this.http.get<Community[]>("http://localhost:8080/community/all", this.token);
  }

  getCommunityById(communityId: number): Observable<Community> {
    return this.http.get<Community>(`http://localhost:8080/community/${communityId}`, this.token);
  }

  createCommunity(profileCommunityDto: ProfileCommunityDTO): Observable<Community> {
    return this.http.post<Community>("http://localhost:8080/profile/create-community", profileCommunityDto, this.token);
  }

}
