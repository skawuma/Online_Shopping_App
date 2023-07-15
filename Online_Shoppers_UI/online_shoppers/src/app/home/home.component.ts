import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Product } from '../_model/product.model';
import { ImageProcessingService } from '../services/image-processing.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  showLoadButton =false;
  pageNumber:number=0;
  productDetails:Product[]=[];
  constructor(private productService: ProductService,
    private imageProcessingService:ImageProcessingService,
    private router :Router
    ) { }

  ngOnInit(): void {
   this.getProducts();

  }


    
  public getProducts(searchKey:any="") {
    this.productService.getAllProducts(this.pageNumber,searchKey)
    
    .pipe(
    map ((x:Product[],i)=>  x.map((product:Product )=>this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) => {
        console.log(resp);
        if(resp.length ==1){
          this.showLoadButton =true;
        }else{
          this.showLoadButton= false;
        }
        resp.forEach(p=> this.productDetails.push(p));
       // this.productDetails=resp;
      }, (error: HttpErrorResponse) => {

        console.log(console.error);
      }


    );
  }

  showProductDetails(productId: any){

    this.router.navigate(['/productVeiwDetails' , {productId: productId}])

  }
  public loadMoreProducts(){
    this.pageNumber= this.pageNumber+1;
    this.getProducts();


  }

public searchBykeyword(searchkeyword:any){
  console .log (searchkeyword);
  this.pageNumber=0;
  this.productDetails =[];
this.getProducts(searchkeyword);

}

}
