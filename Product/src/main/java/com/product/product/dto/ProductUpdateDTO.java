package com.product.product.dto;

public class ProductUpdateDTO {
    public String getProductName() {
        return productName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }



    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }



    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public void setDiscount(Boolean discount) {
        isDiscount = discount;
    }

    public void setISDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getIsDiscount() {
        return isDiscount;
    }

    public Double getDiscount() {
        return discount;
    }

    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Boolean isDiscount;
    private Double discount;
    private Integer quantityInStock;
    private Boolean status;

}
