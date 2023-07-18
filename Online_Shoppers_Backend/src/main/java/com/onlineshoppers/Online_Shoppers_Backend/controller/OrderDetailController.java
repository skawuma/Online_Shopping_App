package com.onlineshoppers.Online_Shoppers_Backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
