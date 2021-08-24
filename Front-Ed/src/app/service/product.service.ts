import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Product } from '../model/Product';
import { ProductDTO } from '../model/ProductDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  registerProduct(productDto: ProductDTO): Observable<Product> {
    return this.http.post<Product>("http://localhost:8080/product/register-product", productDto, this.token);
  }

  getById(productId: number): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/product/${productId}`, this.token);
  }

}
