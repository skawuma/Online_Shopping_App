import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';
import { Observable } from 'rxjs';
import { AnimationStyleMetadata } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:7070';
 
  constructor(private httpclient: HttpClient) { }

  public addProduct(product:FormData){

    return this.httpclient.post<Product>("http://localhost:7070/api/product/addNewProduct", product);
  }

  public getAllProducts(){

return this.httpclient.get<Product[]> ("http://localhost:7070/api/product/getAllProducts");

  }

  public  deleteProduct(productId: number) {
    return this.httpclient.delete("http://localhost:7070/api/product/deleteProductDetails/"
    + productId  
    );
  }
  public getProductDetailsById(productId:any): Observable<any>{
    return this.httpclient.get("http://localhost:7070/api/product/getProductDetailsById/ "
    + productId  
    );
  }

  public getProductDetailsById1(productId:any): Observable<any> {
    return this.httpclient.get(`${this.baseUrl}/api/product/getProductDetailsById/${productId}`, { responseType: 'text' });
  }

  public deleteProduct1(productId: number): Observable<any> {
    return this.httpclient.delete(`${this.baseUrl}/api/product/deleteProductDetails/${productId}`, { responseType: 'text' });
  }
}
