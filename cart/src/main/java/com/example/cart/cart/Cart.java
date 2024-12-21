package com.example.cart.cart;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table
public class Cart {
    public Integer getCartId() {
        return cartId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private String userId;

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Integer productId;
    private Integer quantity;
    private Double price;
    private String productImage;

    public String getProductName() {
        return productName;
    }



    private String productName;
    @CreationTimestamp
  private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    public Cart() {
//    }

//    public Cart(Integer cartId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.cartId = cartId;
//        this.userId = userId;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    public Cart(Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.userId = userId;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    public Integer getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(Integer cartId) {
//        this.cartId = cartId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

//    @Override
//    public String toString() {
//        return "Cart{" +
//                "cartId=" + cartId +
//                ", userId=" + userId +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
}



