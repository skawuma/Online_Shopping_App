import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { MyOrderDetails } from '../_model/order.model';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  displayedColumns: string[] =['Name', 'Address','ContactNo', 'Amount','Status'];
 myOrderDetails:MyOrderDetails[]=[];
  constructor(private productService:ProductService) { }

  ngOnInit(): void {
    this.getOrderDetails();
  }

public getOrderDetails(){

  this.productService.getOrderDetails().subscribe(

    (resp:MyOrderDetails[])=>{
     console.log(resp) ;
     this.myOrderDetails= resp;
    },
    (error)=>{
      console.log(error);
    }
  );
}

}
