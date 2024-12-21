package com.example.cart.cart;

public class CartDTO {

    private String userId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String productName;
    private String productImage;




    public String getProductName() {
        return productName;
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


    public void  setProductName(String productName) {
        this.productName = productName;
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




}
