package com.example.Order.service;

import com.example.Order.dto.OrderDTO;
import com.example.Order.model.Order;
import com.example.Order.model.OrderItem;
import com.example.Order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String createOrder(Order order) {
        if(order.getUserId() == 0) {
            return "User ID is required";
        }
        if(order.getTotalPrice() == 0) {
            return "Total Price is required";
        }
        if(order.getStatus() == null || order.getStatus().isEmpty()) {
            return "Status is required";
        }
        if(order.getAddress() == null || order.getAddress().isEmpty()) {
            return "Address is required";
        }
        if(order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            return "Order Items are required";
        }


        try {
            order.setCreatedAt(LocalDateTime.now());
            if (order.getOrderItems() != null) {
                for (OrderItem item : order.getOrderItems()) {
                    item.setOrderId(order);
                }
            }
            orderRepo.save(order);
            return "Order Created Successfully";
        } catch (Exception e) {
            // Log the error (if logging is set up) and return an error message
            return "Failed to create the order: " + e.getMessage();
        }
    }

    public OrderDTO getOrderById(int orderId) {
        try {
            Order order = orderRepo.findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order with ID " + orderId + " not found"));
            return modelMapper.map(order, OrderDTO.class);
        } catch (NoSuchElementException e) {
            // Handle specific exception
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the order: " + e.getMessage());
        }
    }

    public String updateOrderStatus(int orderId, Order order) {
        try {
            Order orderData = orderRepo.findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order with ID " + orderId + " not found"));

            orderData.setStatus(order.getStatus());
            orderRepo.save(orderData);
            return "Order Updated Successfully";
        } catch (NoSuchElementException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Failed to update order status: " + e.getMessage();
        }
    }

    public List<Order> getOrdersForUser(int userId) {
        try {
            return orderRepo.getOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching orders for user ID " + userId + ": " + e.getMessage());
        }
    }

    public List<Order> getPastOrdersForUser(int userId) {
        try {
            return orderRepo.getPastOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching past orders for user ID " + userId + ": " + e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all orders: " + e.getMessage());
        }
    }
}
