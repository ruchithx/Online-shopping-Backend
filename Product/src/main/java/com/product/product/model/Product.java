package com.product.product.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer SKU;
    private Boolean isDiscount;
    private Double discount;
    private Integer quantityInStock;
    private String status; // sale or not
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @OneToOne
    @JoinColumn(name = "categoryId")
    private Category category;



    @OneToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }



    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSKU() {
        return this.SKU;
    }

    public void setSKU(Integer SKU) {
        this.SKU = SKU;
    }

    public Boolean getIsDiscount() {
        return this.isDiscount;
    }

    public void setIsDiscount(Boolean isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }






}
