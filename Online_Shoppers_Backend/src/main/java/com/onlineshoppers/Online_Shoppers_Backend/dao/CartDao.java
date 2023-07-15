package com.onlineshoppers.Online_Shoppers_Backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;

public interface CartDao extends CrudRepository<Cart,Integer>{
    
}
