package com.example.cart.cart;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer itemId;
    private Integer cartId;
    private String productId;
    private String productName;
    private String productDescription;
    private Double quantity;
    private Double price;
    private Double discount;
@ManyToOne
private Cart cart;


    public CartItem(Integer itemId, Integer cartId, String productId, String productName, String productDescription, Double quantity, Double price, Double discount) {
        this.itemId = itemId;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public CartItem(Integer cartId, String productId, String productName, String productDescription, Double quantity, Double price, Double discount) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public CartItem(String s, String s1, Object o) {
    }

    public CartItem() {

    }


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", productName=" + productName +
                ", productDescription=" + productDescription +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
