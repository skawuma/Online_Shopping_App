package com.onlineshoppers.Online_Shoppers_Backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderDetail;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

public interface  OrderDetailDao  extends  CrudRepository <OrderDetail,Integer> {
    public List<OrderDetail> findByUser(User user);
    
}
