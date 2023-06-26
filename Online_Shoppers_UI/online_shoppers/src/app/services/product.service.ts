import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpclient: HttpClient) { }

  public addProduct(product:FormData){

    return this.httpclient.post<Product>("http://localhost:7070/addNewProduct", product);
  }

  public getAllProducts(){

return this.httpclient.get<Product[]> ("http://localhost:7070/getAllProducts");

  }
}
