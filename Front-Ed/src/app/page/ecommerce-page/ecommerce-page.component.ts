import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ecommerce } from 'src/app/model/Ecommerce';
import { Product } from 'src/app/model/Product';
import { ProductDTO } from 'src/app/model/ProductDTO';
import { EcommerceService } from 'src/app/service/ecommerce.service';

@Component({
  selector: 'app-ecommerce-page',
  templateUrl: './ecommerce-page.component.html',
  styleUrls: ['./ecommerce-page.component.css']
})
export class EcommercePageComponent implements OnInit {

  ecommerce: Ecommerce = new Ecommerce();
  ecommerceId = this.activatedRoute.snapshot.params["id"];

  product: Product = new Product();
  productDto: ProductDTO = new ProductDTO();

  constructor(  
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ecommerceService: EcommerceService
  ) { }

  ngOnInit(): void {
    this.getEcommerceById();
  }


  getEcommerceById() {
    this.ecommerceService.getEcommerceById(this.ecommerceId).subscribe((resp: Ecommerce) => {
      this.ecommerce = resp;
      console.log(this.ecommerce);
    });
  }

}
