package com.onlineshoppers.Online_Shoppers_Backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderDetail;

public interface  OrderDetailDao  extends  CrudRepository <OrderDetail,Integer> {
    
}
