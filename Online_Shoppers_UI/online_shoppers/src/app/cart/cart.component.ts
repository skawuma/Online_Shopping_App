import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  displayedColumns: string[] = [ 'Product Name', 'Description','Product Actual Price', 'Product Discounted Price','Action'];
  cartDetails: any[] =[];
  constructor( private productService:ProductService,

    private router:Router
    ) { }

  ngOnInit(): void {
   this.getCartDetails();
  }

  getCartDetails(){
this.productService.getCartDetails().subscribe(

  (response:any)=>{
    console.log(response);
    this.cartDetails =response;

  },
  (error)=>{
    console.log(error);
  }
);

  }
  checkOut(){


    this.router.navigate(['/buyProduct', {isSingleProductCheckout: false, id:0}]);
    // this.productService.getProductDetails(false,0).subscribe(

    //   (response:any)=>{
    //     console.log(response);
    //   },
    //   (error)=>{
    //     console.log(error);
    //   }
    // );

  }

  public deleteCartItem(cartId: number) {
    this.productService.deleteCartItem(cartId).subscribe(
      (resp) => {
        this.getCartDetails();
      },

      (error: HttpErrorResponse) => {

        console.log(error);
      }
    );
  }

}
