package com.onlineshoppers.Online_Shoppers_Backend.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

public interface UserDao extends CrudRepository<User,String> {

    
    
}
