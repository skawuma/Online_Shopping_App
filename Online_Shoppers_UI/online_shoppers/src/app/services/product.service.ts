import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpclient: HttpClient) { }

  public addProduct(product:Product){
    
    return this.httpclient.post<Product>("http://localhost:7070/addNewProduct", product);


  }
}
