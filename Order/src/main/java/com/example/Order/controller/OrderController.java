package com.example.Order.controller;

import com.example.Order.dto.OrderDTO;
import com.example.Order.model.Order;
import com.example.Order.service.OrderItemService;
import com.example.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private OrderItemService orderItemService;



    @PostMapping("orders")
    public String createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("orders/{orderId}")
    public OrderDTO getOrdersById(@PathVariable int orderId){
        return orderService.getOrderById(orderId);
    }
    @PutMapping("admin/orders/{orderId}")
    public String updateOrderStatus(@PathVariable int orderId,@RequestBody Order order){
        return orderService.updateOrderStatus(orderId,order);
    }

    @GetMapping("orders")
    public List<Order> getOrdersForUser(@RequestParam int userId){
        return orderService.getOrdersForUser(userId);
    }

    @GetMapping("orders/past")
    public List<Order> getPastOrdersForUser(@RequestParam int userId){
        return orderService.getPastOrdersForUser(userId);
    }

    @GetMapping("admin/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }


}
