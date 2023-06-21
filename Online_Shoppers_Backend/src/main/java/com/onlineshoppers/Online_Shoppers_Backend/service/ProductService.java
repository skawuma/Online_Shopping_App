package com.onlineshoppers.Online_Shoppers_Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshoppers.Online_Shoppers_Backend.dao.ProductDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


public Product addNewPdroduct(Product product){

    return productDao.save(product);
}


    }
    

