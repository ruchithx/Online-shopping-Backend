package com.example.Order.dto;

import com.example.Order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private int id;
    private int userId;
    private float totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private String address;
    private List<OrderItem> orderItems;

}
