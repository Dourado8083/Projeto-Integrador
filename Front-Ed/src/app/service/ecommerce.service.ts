import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Ecommerce } from '../model/Ecommerce';
import { EcommerceDTO } from '../model/EcommerceDTO';

@Injectable({
  providedIn: 'root'
})
export class EcommerceService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getEcommerceById(ecommerceId: number):Observable<Ecommerce>{
    return this.http.get<Ecommerce>(`http://localhost:8080/ecommerce/${ecommerceId}`, this.token)
  }

  createEcommerce(ecommerceDto: EcommerceDTO): Observable<Ecommerce> {
    return this.http.post<Ecommerce>("http://localhost:8080/ecommerce/create-ecommerce", ecommerceDto, this.token);
  }


}
