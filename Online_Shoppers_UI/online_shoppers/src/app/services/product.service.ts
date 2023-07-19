import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';
import { Observable } from 'rxjs';
import { AnimationStyleMetadata } from '@angular/animations';
import { OrderDetails } from '../_model/order-detail.model';
import { MyOrderDetails } from '../_model/order.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl = 'http://localhost:7070';
 
  constructor(private httpclient: HttpClient) { }

  public addProduct(product:FormData){

    return this.httpclient.post<Product>("http://localhost:7070/api/product/addNewProduct", product);
  }

  public getAllProducts(pageNumber:number,searchKeyword:any=""){

return this.httpclient.get<Product[]> ("http://localhost:7070/api/product/getAllProducts?pageNumber="+pageNumber+"&searchKey="+searchKeyword);

  }

  public  deleteProduct(productId: number) {
    return this.httpclient.delete("http://localhost:7070/api/product/deleteProductDetails/"
    + productId  
    );
  }
  public getProductDetailsById(productId:any): Observable<any>{
    return this.httpclient.get("http://localhost:7070/api/product/getProductDetailsById/"
    + productId  
    );
  }

  public  getProductDetails(isSingleProductCheckout: any,productId: any) {
    return this.httpclient.get<Product[]>("http://localhost:7070/api/product/getProductDetails/"+isSingleProductCheckout+"/"
    + productId  
    );
  }

  public placeOrder(orderDetails:OrderDetails,isCartCheckedout:any){

    return this.httpclient.post("http://localhost:7070/placeOrder/"+isCartCheckedout,orderDetails);

  }

  public addToCart(productId:any){
    return this.httpclient.get("http://localhost:7070/addToCart/" + productId);
  }

  public markOrderAsDelivered(orderId:number){
    return this.httpclient.get("http://localhost:7070/markOrderAsDelivered/" + orderId);
  }


  public getCartDetails(){
    return this.httpclient.get("http://localhost:7070/getCartDetails"); 
  }
  public  deleteCartItem(cartId: number) {
    return this.httpclient.delete("http://localhost:7070/deleteCartItem/"
    + cartId 
    );
  }


  public getOrderDetails(): Observable<MyOrderDetails[]>{
    return this.httpclient.get<MyOrderDetails[]>("http://localhost:7070/getOrderDetails"); 
  }

  public getAllOrderDetails(): Observable<MyOrderDetails[]>{
    return this.httpclient.get<MyOrderDetails[]>("http://localhost:7070/getAllOrderDetails"); 
  }
}
