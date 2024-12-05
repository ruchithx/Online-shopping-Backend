package com.example.Order.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Double productPrice;
    private Boolean isDiscount;
    private Double discount;
    private String productName;
    private String mediaUrl;

}
