import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Profile } from '../model/Profile';
import { ProfileDTO } from '../model/ProfileDTO';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getProfileById(profileId: number): Observable<Profile> {
    return this.http.get<Profile>(`http://localhost:8080/profile/${profileId}`, this.token);
  }

  editBio(profileDto: ProfileDTO): Observable<Profile>{
    return this.http.put<Profile>("http://localhost:8080/profile/edit-bio", profileDto, this.token)
  } 
  
  editHeader(profileDto: ProfileDTO): Observable<Profile>{
    return this.http.put<Profile>("http://localhost:8080/profile/edit-header", profileDto, this.token)
  } 

}