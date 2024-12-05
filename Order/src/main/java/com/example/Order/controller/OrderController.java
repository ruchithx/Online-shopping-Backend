package com.example.Order.controller;

import com.example.Order.dto.OrderDTO;
import com.example.Order.model.Order;
import com.example.Order.model.OrderResponse;
import com.example.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {

        try {
            String result = orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order: " + e.getMessage());
        }
    }

    @GetMapping("orders/{orderId}")
    public ResponseEntity<?> getOrdersById(@PathVariable int orderId) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(orderId);
            return ResponseEntity.ok(orderDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("admin/orders/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable int orderId, @RequestBody Order order) {
        try {
            String result = orderService.updateOrderStatus(orderId, order);
            if (result.equals("Order not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update order status: " + e.getMessage());
        }
    }

    @GetMapping("orders")
    public ResponseEntity<?> getOrdersForUser(@RequestParam int userId) {
        try {
            List<Order> orders = orderService.getOrdersForUser(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("orders/past")
    public ResponseEntity<?> getPastOrdersForUser(@RequestParam int userId) {
        try {
            List<Order> pastOrders = orderService.getPastOrdersForUser(userId);
            return ResponseEntity.ok(pastOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("admin/orders")
    public ResponseEntity<?> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
