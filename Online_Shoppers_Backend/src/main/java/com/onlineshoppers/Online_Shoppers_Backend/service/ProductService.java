package com.onlineshoppers.Online_Shoppers_Backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineshoppers.Online_Shoppers_Backend.dao.ProductDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  public Product addNewPdroduct(Product product) {

    return productDao.save(product);
  }

  public List<Product> getAllProducts(int pageNumber) {
    Pageable pageable =PageRequest.of(pageNumber,3);

    return (List<Product>) productDao.findAll( pageable);
  }

  public void deleteProductDetails(Integer productId) {

    productDao.deleteById(productId);
  }

  public Product getProductDetailsById(Integer productId) {

    return productDao.findById(productId).get();
  }

  public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {

    if (isSingleProductCheckout) {
      // perfoms Function when buying a single Product

      List<Product> list = new ArrayList<>();
      Product product = productDao.findById(productId).get();
      list.add(product);
      return list;
    } else {
      //Check Out the Entire Cart

    }
    return new ArrayList<>();
  }

}
