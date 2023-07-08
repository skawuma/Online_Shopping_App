package com.onlineshoppers.Online_Shoppers_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderInput;
import com.onlineshoppers.Online_Shoppers_Backend.service.OrderDetailService;

// @RequestMapping
@RestController
public class OrderDetailController {

 @Autowired
 OrderDetailService orderDetailService;
     
   // @PreAuthorize("hasRole('User')")
     @PostMapping({"/placeOrder"})
    public void placeOrder(@RequestBody OrderInput orderInput){            
    {
      try {
            orderDetailService.placeOrder(orderInput);
           } catch (Exception ex) {  
         ex.printStackTrace();

    }
    }
    
}
}
