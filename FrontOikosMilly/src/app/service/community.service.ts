import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Community } from '../model/community';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getAllCommunity(): Observable<Community[]>{
    return this.http.get<Community[]>('http://localhost:8080/community/all',this.token)
  }

  postCommunity(community: Community): Observable<Community>{
    return this.http.post<Community>('http://localhost:8080/community/create', community,this.token)
  }
}
