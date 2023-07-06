import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Product } from '../_model/product.model';
import { ImageProcessingService } from '../services/image-processing.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  

  productDetails:Product[]=[];
  constructor(private productService: ProductService,
    private imageProcessingService:ImageProcessingService,
    ) { }

  ngOnInit(): void {
   this.getProducts();

  }



  public getProducts() {
    this.productService.getAllProducts()
    
    .pipe(
    map ((x:Product[],i)=>  x.map((product:Product )=>this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) => {
        console.log(resp);
        this.productDetails=resp;
      }, (error: HttpErrorResponse) => {

        console.log(console.error);
      }


    );
  }


}
