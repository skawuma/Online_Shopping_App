import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { MyOrderDetails } from '../_model/order.model';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  displayedColumns: string[] = ['Name', 'Address', 'ContactNo', 'Amount', 'Status', 'Action'];
  myOrderDetails: MyOrderDetails[] = [];
  status: string= 'All'
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllOrderDetails(this.status);
  }

  public getAllOrderDetails( statusParam:string) {

    this.productService.getAllOrderDetails(statusParam).subscribe(

      (resp: MyOrderDetails[]) => {
        console.log(resp);
        this.myOrderDetails = resp;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  public markAsDelivered(orderId: number) {

    console.log(orderId);
    this.productService.markOrderAsDelivered(orderId).subscribe(

      (resp) => {
        this.getAllOrderDetails(this.status);
        console.log(resp);

      },
      (error) => {
        console.log(error);
      }
    );

  }

}
