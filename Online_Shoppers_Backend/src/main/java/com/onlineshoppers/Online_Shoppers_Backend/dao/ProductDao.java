package com.onlineshoppers.Online_Shoppers_Backend.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;

public interface ProductDao   extends CrudRepository<Product,Integer>{

    List<Product> findAll(Pageable pageable);

    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase
                          (String key1,String key2, Pageable pageable);

    
    
}
