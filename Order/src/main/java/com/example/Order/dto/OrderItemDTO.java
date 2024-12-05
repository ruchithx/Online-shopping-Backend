package com.example.Order.dto;

import com.example.Order.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private int id;
    private int orderId;
    private int productId;
    private float quantity;
    private ProductDTO product;  // Add ProductDTO to include product details
}
