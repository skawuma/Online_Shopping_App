package com.onlineshoppers.Online_Shoppers_Backend.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import com.onlineshoppers.Online_Shoppers_Backend.entity.ImageModel;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Product;
import com.onlineshoppers.Online_Shoppers_Backend.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Delete-Header")

@EnableWebMvc
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = { "/addNewProduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Product addNewProduct(@RequestPart Product product,
            @RequestPart("imageFile") MultipartFile[] file) {

        try {

            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewPdroduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {

        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(

                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());
            imageModels.add(imageModel);

        }

        return imageModels;
    }
    
   
    @GetMapping({ "/getAllProducts" })
    public List<Product> getAllProducts() {
        return productService.getAllProducts();

    }
    // @CrossOrigin(allowedHeaders = {"Authorization", "Origin"})
    // @PreAuthorize("hasRole('Admin')")

    @CrossOrigin(origins = {
            "http://localhost:4200"
    }, allowedHeaders = "X-AUTH-TOKEN", allowCredentials = "false", maxAge = 15 * 60, methods = {
            RequestMethod.DELETE

    })
   @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({ "/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId) {
        productService.deleteProductDetails(productId);
    }
@PreAuthorize("hasRole('Admin')")    
@GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById( @PathVariable("productId") Integer productId){
        return productService.getProductDetailsById(productId);

    }

}
