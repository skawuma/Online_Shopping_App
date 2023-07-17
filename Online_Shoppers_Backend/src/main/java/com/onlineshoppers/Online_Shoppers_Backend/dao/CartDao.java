package com.onlineshoppers.Online_Shoppers_Backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

public interface CartDao extends CrudRepository<Cart,Integer>{


    public List<Cart> findByUser(User user);
    
}
