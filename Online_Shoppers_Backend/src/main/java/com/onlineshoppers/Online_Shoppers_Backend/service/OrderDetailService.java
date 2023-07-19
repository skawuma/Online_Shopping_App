package com.onlineshoppers.Online_Shoppers_Backend.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshoppers.Online_Shoppers_Backend.configurations.JwtRequestFilter;
import com.onlineshoppers.Online_Shoppers_Backend.dao.CartDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.OrderDetailDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.ProductDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.UserDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderDetail;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderInput;
import com.onlineshoppers.Online_Shoppers_Backend.entity.OrderProductQuantity;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

@Service
public class OrderDetailService {

private static final  String ORDER_PLACED ="Placed";

@Autowired
private UserDao userDao;  

 @Autowired
private OrderDetailDao orderDetailsDao;

@Autowired
private ProductDao productDao;

@Autowired
private JwtRequestFilter jwtFilter;

@Autowired
private CartDao cartDao;


 


  public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout ) {

  List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

  for (OrderProductQuantity o : productQuantityList) {
    Product product = productDao.findById(o.getProductId()).get();

    // Product product = productDao.findById(o.getId()).get();

    String currentUser = jwtFilter.CURRENT_USER;

    User user = userDao.findById(currentUser).get();

    OrderDetail orderDetail = new OrderDetail(

        orderInput.getFullName(),
        orderInput.getFullAddress(),
        orderInput.getContactNumber(),
        orderInput.getAlternateContactNumber(),
        ORDER_PLACED,

        product.getProductDiscountedPrice() * o.getQuantity(),

        product, user

    );

    //Empty  the Cart
    if(!isSingleProductCheckout){
      List<Cart> carts = cartDao.findByUser(user);
      carts.stream().forEach(x-> cartDao.deleteById (x.getCartId()));

    }

    orderDetailsDao.save(orderDetail);
  }

}


public List<OrderDetail> getOrderDetails() {

  String username = JwtRequestFilter.CURRENT_USER;
    User user = userDao.findById(username).get();

    return orderDetailsDao.findByUser(user);

}


      public List<OrderDetail> getAllOrderDetails(){

         List<OrderDetail>  orderDetails =new ArrayList<>();
        orderDetailsDao.findAll().forEach(
          x-> orderDetails.add(x)
        );
        return orderDetails;
  
       // return (List<OrderDetail>) orderDetailsDao.findAll();
      }

      }

  
    

