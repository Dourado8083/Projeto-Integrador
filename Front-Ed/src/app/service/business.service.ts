import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Business } from '../model/Business';
import { ProfileBusinessDTO } from '../model/ProfileBusinessDTO';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }
  
  createBusiness(profileBusinessDto: ProfileBusinessDTO): Observable<Business> {
    return this.http.post<Business>("http://localhost:8080/business/create-business", profileBusinessDto, this.token);
  }
  
  getBusinessById(businessId: number):Observable<Business>{
    return this.http.get<Business>(`http://localhost:8080/business/${businessId}`, this.token)
  }
}
