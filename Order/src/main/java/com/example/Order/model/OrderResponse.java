package com.example.Order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderResponse {
    private int id;
    private String userId;
    private double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private String address;
    private List<OrderItemResponse> orderItems;

    // Constructor, Getters, and Setters
}

