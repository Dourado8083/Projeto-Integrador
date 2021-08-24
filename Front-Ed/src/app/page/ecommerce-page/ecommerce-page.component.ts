import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ecommerce } from 'src/app/model/Ecommerce';
import { Product } from 'src/app/model/Product';
import { ProductDTO } from 'src/app/model/ProductDTO';
import { EcommerceService } from 'src/app/service/ecommerce.service';
import { ProductService } from 'src/app/service/product.service';

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

  pList: Product [];

  constructor(  
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private ecommerceService: EcommerceService,
    private productService: ProductService
  ) { }

  ngOnInit(): void {

    window.scroll(0,0)

    this.getEcommerceById();
  }


  getEcommerceById() {
    this.ecommerceService.getEcommerceById(this.ecommerceId).subscribe((resp: Ecommerce) => {
      this.ecommerce = resp;
      console.log(this.ecommerce);
    });
  }

  cadastrar(){
    this.productDto.EcommerceOnId = this.ecommerceId
    alert(this.ecommerceId)
    alert(this.productDto.productName)
    alert(this.productDto.productDescription)
    alert(this.productDto.productPrice)

    this.productService.registerProduct(this.productDto).subscribe((resp: Product)=>{
      alert('foiiii')
      this.productDto = new ProductDTO()
    })
  }

}
