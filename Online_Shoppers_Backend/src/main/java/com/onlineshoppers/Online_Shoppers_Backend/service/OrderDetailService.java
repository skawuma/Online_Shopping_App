package com.onlineshoppers.Online_Shoppers_Backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshoppers.Online_Shoppers_Backend.configurations.JwtRequestFilter;
import com.onlineshoppers.Online_Shoppers_Backend.dao.OrderDetailDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.ProductDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.UserDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderDetail;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderInput;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderProductQuantity;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

@Service
public class OrderDetailService {

private static final  String ORDER_PLACED ="Placed";

@Autowired
UserDao userDao;  

 @Autowired
OrderDetailDao orderDetailsDao;

@Autowired
ProductDao productDao;

@Autowired
JwtRequestFilter jwtFilter;


      public  void placeOrder(OrderInput orderInput){

  List<OrderProductQuantity> productQuantityList=  orderInput.getOrderProductQuantityList();
   
     for (OrderProductQuantity o:productQuantityList){ 
    Product product = productDao.findById(o.getProductId()).get();

     //Product product = productDao.findById(o.getId()).get();

      String currentUser = jwtFilter.CURRENT_USER; 
    
     User user = userDao.findById(currentUser).get();
       
OrderDetail orderDetail = new OrderDetail(
 
       orderInput.getFullName(),  
       orderInput.getFullAddress() , 
       orderInput.getContactNumber(),
       orderInput.getAlternateContactNumber(), 
       ORDER_PLACED,
     
     product.getProductActualPrice()*  o.getQuantity(),

        product, user

);

 orderDetailsDao.save( orderDetail);
   }

    }


      }

  
    

