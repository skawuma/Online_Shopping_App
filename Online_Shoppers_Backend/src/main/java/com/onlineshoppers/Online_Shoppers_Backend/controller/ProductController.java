package com.onlineshoppers.Online_Shoppers_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.service.ProductService;

@RestController
public class ProductController {

@Autowired
 private  ProductService productService;

    @PostMapping({"/addNewProduct"})
    public Product addNewProduct( @RequestBody Product product){

        return productService .addNewPdroduct(product);

    }
    
}
