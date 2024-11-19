package com.product.product.dto;

public class ProductDTO {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer SKU;
    private Boolean isDiscount;
    private Double discount;
    private Integer quantityInStock;
    private Boolean status;
    private Long categoryId;
    private Long brandId;

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

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCategoryId() {

        return this.categoryId;

    }

    public void setCategoryId(Long categoryId) {

        this.categoryId = categoryId;

    }

    public Long getBrandId() {

        return this.brandId;

    }

    public void setBrandId(Long brandId) {

        this.brandId = brandId;

    }


    public Integer getProductQuantity() {
        return this.quantityInStock ;
    }
}
