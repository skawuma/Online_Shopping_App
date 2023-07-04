import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Product } from '../_model/product.model';
import { Observable, map, of } from 'rxjs';
import { ProductService } from './product.service';
import { ImageProcessingService } from './image-processing.service';

@Injectable({
  providedIn: 'root'
})
export class ProductResolveService implements Resolve<Product> {

  constructor(private productService: ProductService,
             private imageProcessingService: ImageProcessingService
    ) { }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
   const id = route.paramMap.get("productId");


  if(id)
  {
    //fetch Details from Backend
    return this.productService.getProductDetailsById(id)
    .pipe(
      map(p=> this.imageProcessingService.createImages(p))
    );

  }else{
    //Return Empty product Observable
   return of(this.getProductDetaiils());
  }

  }
getProductDetaiils(){
return{
  productId: null,
  productName: '',
  productDescription: '',
  productDiscountedPrice: 0,
  productActualPrice: 0,
  productImages: [],

}; 

  
}

}
