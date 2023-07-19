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

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllOrderDetails();
  }

  public getAllOrderDetails() {

    this.productService.getAllOrderDetails().subscribe(

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
        this.getAllOrderDetails();
        console.log(resp);

      },
      (error) => {
        console.log(error);
      }
    );

  }

}
