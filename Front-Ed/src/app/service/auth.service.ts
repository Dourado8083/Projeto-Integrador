import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Profile } from '../model/Profile';
import { ProfileLoginDTO } from '../model/ProfileLoginDTO';

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

  loggedIn(): boolean {
    return environment.token != "";
  }

}
