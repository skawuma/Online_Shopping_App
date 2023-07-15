package com.onlineshoppers.Online_Shoppers_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;
import com.onlineshoppers.Online_Shoppers_Backend.service.CartService;


@RestController
public class CartController {


  @Autowired
  private  CartService cartService;

    @PreAuthorize("hasRole('User')")
     @GetMapping({"/addToCart/{productId}"})
    public Cart addToCart(@PathVariable(name = "productId")Integer productId)
    {
   return cartService.addToCart(productId);
    }
    
}
