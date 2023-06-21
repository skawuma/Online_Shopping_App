package com.onlineshoppers.Online_Shoppers_Backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;

public interface ProductDao   extends CrudRepository<Product,Integer>{

    
    
}
