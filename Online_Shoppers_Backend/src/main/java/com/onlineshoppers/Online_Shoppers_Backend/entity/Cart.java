package com.onlineshoppers.Online_Shoppers_Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Cart{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer cartId;

 @OneToOne
private Product product;

 @OneToOne
private User user;



public Cart(Product product, User user) {
    this.product = product;
    this.user = user;
}

public Integer getCartId() {
    return cartId;
}

public void setCartId(Integer cartId) {
    this.cartId = cartId;
}

public Product getProduct() {
    return product;
}

public void setProduct(Product product) {
    this.product = product;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}

}