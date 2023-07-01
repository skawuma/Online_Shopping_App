import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';
import { Observable } from 'rxjs';

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
   
    // , {
    //   headers: new HttpHeaders()
    //   .set('Content-Type', "application/json")
    //   .set('Access-Control-Allow-Origin', '*')
     

    // }
    
    );
  }

  public deleteProduct1(productId: number): Observable<any> {
    return this.httpclient.delete(`${this.baseUrl}/api/product/deleteProductDetails/${productId}`, { responseType: 'text' });
  }
}
