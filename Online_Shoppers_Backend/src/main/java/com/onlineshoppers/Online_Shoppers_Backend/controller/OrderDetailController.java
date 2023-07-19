package com.onlineshoppers.Online_Shoppers_Backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderDetail;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderInput;
import com.onlineshoppers.Online_Shoppers_Backend.service.OrderDetailService;

// @RequestMapping
@RestController
public class OrderDetailController {

 @Autowired
 OrderDetailService orderDetailService;
     
   // @PreAuthorize("hasRole('User')")
     @PostMapping({"/placeOrder/{isSingleProductCheckout}"}) 
    public void placeOrder(@PathVariable(name="isSingleProductCheckout") boolean isSingleProductCheckout,
      @RequestBody OrderInput orderInput){            
    {
      try {
            orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
           } catch (Exception ex) {  
         ex.printStackTrace();

    }
    }
    
}
    @PreAuthorize("hasRole('User')")
  @GetMapping({ "/getOrderDetails" })
  public List<OrderDetail> getOrderDetails() {
    return orderDetailService.getOrderDetails();
  }

   @PreAuthorize("hasRole('Admin')")
  @GetMapping({ "/getAllOrderDetails/{status}" })
  public List<OrderDetail> getAllOrderDetails(@PathVariable(name="status")String status
                                              ) {
    return orderDetailService.getAllOrderDetails(status);
  }

 @PreAuthorize("hasRole('Admin')")
  @GetMapping({"/markOrderAsDelivered/{orderId}"})
  public void markOrderAsDelivered(@PathVariable(name= "orderId") Integer orderId){
   orderDetailService.markOrderAsDelivered(orderId);
  }
}
