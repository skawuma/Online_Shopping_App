import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { OrderDetails } from '../_model/order-detail.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../_model/product.model';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent implements OnInit {
  productDetails:Product[]=[];

orderDetails:OrderDetails={
  fullName: '',
  fullAddress: '',
  contactNumber: '',
  alternateContactNumber: '',
  orderProductQuantityList: []
}
// product: any;


  constructor(private activatedRoute:ActivatedRoute,
    private productService:ProductService,
    private router:Router
    ) { }

  ngOnInit(): void {

   this.productDetails = this.activatedRoute.snapshot.data['productDetails'];
   this.productDetails.forEach(
    x=> this.orderDetails.orderProductQuantityList.push(
      {productId:x.productId,quantity:1}
    )

   );
   console.log(this.productDetails);
   console.log(this.orderDetails);
  }

   public placeOrder(orderForm: NgForm){
    this.productService.placeOrder(this.orderDetails).subscribe(
      (resp)=>{
       console.log(resp);
       orderForm.reset();
       this.router.navigate(["/OrderConfirm"])
      },
      (err)=> {
        console.log(err)
      }
    );
    
  }

  getQuantityForProduct(productId:number){
 const filteredProduct = this .orderDetails.orderProductQuantityList.filter(
   (productQuantity)=> productQuantity.productId === productId
  );
  return filteredProduct[0].quantity;
  }
  getCalculatedTotal(productId: number,productDiscountedPriice: number){

    const filteredProduct = this .orderDetails.orderProductQuantityList.filter(
      (productQuantity)=> productQuantity.productId === productId
     );
     return filteredProduct[0].quantity * productDiscountedPriice

  }

  onQuantityChanged(quantity:any, productId:number){

    this .orderDetails.orderProductQuantityList.filter(
      (productProduct)=> productProduct.productId === productId
     )[0].quantity = quantity;


  }
  getCalculatedGrandTotal(){

    let grandTotal =0;
    this.orderDetails.orderProductQuantityList.forEach(
      (productQuantity) =>{
      const price = this.productDetails.filter(product =>product.productId===productQuantity.productId)[0].productDiscountedPrice;
    grandTotal = grandTotal+price * productQuantity.quantity;
    }
    );
    return grandTotal
  }

}
