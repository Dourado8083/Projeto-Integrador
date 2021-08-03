import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../model/profile';
import { profileLoginDTO } from '../model/profileLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(profileLogin: profileLoginDTO): Observable<profileLoginDTO> {
    return this.http.post<profileLoginDTO>('http://localhost:8080/profile/credentials', profileLogin)
  }

  cadastrar(profile: Profile): Observable<Profile> {
    return this.http.post<Profile>('http://localhost:8080/profile/signup', profile)
  }

}
