import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../model/Profile';
import { ProfileLoginDTO } from '../model/profileLoginDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  signIn(profileLogin: ProfileLoginDTO): Observable<ProfileLoginDTO> {
    return this.http.post<ProfileLoginDTO>('http://localhost:8080/profile/signin', profileLogin)
  }

  cadastrar(profile: Profile): Observable<Profile> {
    return this.http.post<Profile>('http://localhost:8080/profile/signup', profile)
  }

}
