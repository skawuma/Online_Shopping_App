package com.onlineshoppers.Online_Shoppers_Backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineshoppers.Online_Shoppers_Backend.configurations.JwtRequestFilter;
import com.onlineshoppers.Online_Shoppers_Backend.dao.CartDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.ProductDao;
import com.onlineshoppers.Online_Shoppers_Backend.dao.UserDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Cart;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

   @Autowired
  private UserDao userDao;
  
   @Autowired
  private CartDao cartDao;

  public Product addNewPdroduct(Product product) {

    return productDao.save(product);
  }

  public List<Product> getAllProducts(int pageNumber,String searchKey) {
    Pageable pageable =PageRequest.of(pageNumber,1);
    if (searchKey.equals("")){
    return (List<Product>) productDao.findAll( pageable);
    }else{

    return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey,searchKey,pageable);
                       
    }
  }

  public void deleteProductDetails(Integer productId) {

    productDao.deleteById(productId);
  }

  public Product getProductDetailsById(Integer productId) {

    return productDao.findById(productId).get();
  }

  public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {

    if (isSingleProductCheckout && productId!=0) {
      // perfoms Function when buying a single Product

      List<Product> list = new ArrayList<>();
      Product product = productDao.findById(productId).get();
      list.add(product);
      return list;
    } else {
      //Check Out the Entire Cart

     String username =JwtRequestFilter.CURRENT_USER;
     User user  = userDao.findById(username).get();
     List<Cart> carts =cartDao.findByUser(user);

     return carts.stream().map(x-> x.getProduct()).collect(Collectors.toList());

    }
    // return new ArrayList<>();
  }

}
