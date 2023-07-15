import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../_model/product.model';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ShowProductImagesDialogComponent } from '../show-product-images-dialog/show-product-images-dialog.component';
import { ImageProcessingService } from '../services/image-processing.service';
import { map } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.css']
})
export class ShowProductDetailsComponent implements OnInit {
  showTable= false;
  showLoadButton =false;
  pageNumber:number=0;
  productDetails:Product[]=[];
  displayedColumns: string[] = ['Id', 'Product Name', 'description', 'Product Discounted Price','Product Actual Price','Actions'];


  constructor(private productService: ProductService,
    
  public imagesDialog:MatDialog,
  private imageProcessingService:ImageProcessingService,
  private router:Router
  ) 
    { }

  ngOnInit(): void {
    this.getAllProducts();
  }
 

  public getAllProducts(searchKey:any="") {
    this.showTable =false;
    this.productService.getAllProducts(this.pageNumber,searchKey)
    

    .pipe(
    map ((x:Product[],i)=>  x.map((product:Product )=>this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) => {
        console.log(resp);
        if(resp.length ==1 ){
          this.showLoadButton =true;
        }else{
          this.showLoadButton= false;
        }
        resp.forEach(p=> this.productDetails.push(p));
        console.log(this.productDetails);
        this.showTable =true;
        //this.productDetails=resp;
      }, (error: HttpErrorResponse) => {

        console.log(console.error);
      }


    );
  }
  public deleteProduct(productId: number) {
    this.productService.deleteProduct(productId).subscribe(
      (resp) => {
        this.getAllProducts();
      },

      (error: HttpErrorResponse) => {

        console.log(error);
      }
    );
  }

  showImages(product: Product) {
   console.log(product);
   this.imagesDialog.open(ShowProductImagesDialogComponent,
    {
      data:{
      images:product.productImages
      },
      height: '500px',
      width: '800px',
    }
    );
    }

    editProductDetails(productId: number){
    console.log(productId)
    this.router.navigate(['/addNewproduct',{productId:productId}])


    }

    public loadMoreProducts(){
      this.pageNumber= this.pageNumber+1;
      this.getAllProducts();
  
  
    }
    public searchBykeyword(searchkeyword:any){
      console .log (searchkeyword);
      this.pageNumber=0;
      this.productDetails =[];
    this.getAllProducts(searchkeyword);
    
    }
    
}
