import { Component, OnInit } from '@angular/core';
import { Product } from '../_model/product.model';
import { NgForm } from '@angular/forms';
import { ProductService } from '../services/product.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FileHandle } from '../_model/file-handle.model';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-add-newproduct',
  templateUrl: './add-newproduct.component.html',
  styleUrls: ['./add-newproduct.component.css']
})
export class AddNewproductComponent implements OnInit {
[x: string]: any;

  product: Product ={
    productName: '',
    productDescription: '',
    productDiscountedPrice: 0,
    productActualPrice: 0,
    productImages: []
  }


  constructor( private productService:ProductService, 
  private sanitizer:  DomSanitizer){ }

  ngOnInit(): void {


  }

  addProduct(productForm: NgForm) {

    const  productFormData =this.prepareFormData(this.product);
    this.productService.addProduct(productFormData).subscribe(
      (response: Product) => {
        productForm.reset();
        this.product.productImages=[];
      }, 
      (error: HttpErrorResponse) => {
        console.log(error);
      }

    );
  }
prepareFormData(product:Product):FormData{
const formData = new FormData();
formData.append('product',
new Blob([JSON.stringify(product)], {type: 'application/json'})
);
for(var i=0; i<product.productImages.length;i++){

  formData.append(
    'imageFile',
    product.productImages[i].file,
    product.productImages[i].file.name

  );
}
    return formData;  
}


  onFileSelected(event: any) {
    if(event.target.files){
    console.log(event);

    const targFile = event.target.files[0];
    const fileHandle: FileHandle = {
      file: targFile,
      url: this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(targFile)

      )
    }
      this.product.productImages.push(fileHandle); 
}
}
removeImages(i: number)
{
this.product.productImages.splice(i,1)

}

fileDropped(fileHandle:FileHandle){
this.product.productImages.push(fileHandle );
  
}
}