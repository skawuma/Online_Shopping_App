package com.onlineshoppers.Online_Shoppers_Backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

public interface UserDao extends CrudRepository<User,String> {


    @Query(value = "SELECT * FROM OnlineShoppers.user where user_name = ?1", nativeQuery=true)
	public Optional <User> findByUserName(String userName);
    
    
}
