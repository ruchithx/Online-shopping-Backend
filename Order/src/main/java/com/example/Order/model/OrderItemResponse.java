package com.example.Order.model;

import com.example.Order.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    private int id;
    private double quantity;
    private ProductDTO product;

    // Constructor, Getters, and Setters
}

