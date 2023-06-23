package com.onlineshoppers.Online_Shoppers_Backend.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onlineshoppers.Online_Shoppers_Backend.entity.ImageModel;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = { "/addNewProduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Product addNewProduct(@RequestPart Product product,
            @RequestPart("imageFile") MultipartFile[] file) {
        
        try  {

            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewPdroduct(product);    
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
 

    }

    public Set <ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException{

        Set<ImageModel> imageModels = new HashSet<>();

                for(MultipartFile file: multipartFiles){
               ImageModel imageModel = new ImageModel(
    
         file.getOriginalFilename(),
         file.getContentType(),
       file.getBytes()
       );
   imageModels.add(imageModel);    

}

return imageModels;
              }

}
  