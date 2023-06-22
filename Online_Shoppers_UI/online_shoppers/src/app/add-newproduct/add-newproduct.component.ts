import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product.model';
import { NgForm } from '@angular/forms';
import { ProductService } from '../services/product.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-newproduct',
  templateUrl: './add-newproduct.component.html',
  styleUrls: ['./add-newproduct.component.css']
})
export class AddNewproductComponent implements OnInit {

  product: Product ={
    productName: '',
    productDescription: '',
    productDiscountedPrice: 0,
    productActualPrice: 0
  }

  constructor( private productService:ProductService) { }

  ngOnInit(): void {


  }

  addProduct(productForm: NgForm){

  this.productService.addProduct(this.product).subscribe(
    (response:Product)=>{
      productForm.reset();
  },
  (error:HttpErrorResponse)=> {
    console.log(error);
  }
  
);
}
}