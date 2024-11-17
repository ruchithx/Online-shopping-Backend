package com.example.Order.service;

import com.example.Order.dto.OrderDTO;
import com.example.Order.model.Order;
import com.example.Order.model.OrderItem;
import com.example.Order.repo.OrderItemRepo;
import com.example.Order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        if (order.getOrderItems() != null){
            for (OrderItem item: order.getOrderItems()
                 ) {
                item.setOrderId(order);
            }
        }
        orderRepo.save(order);
        return "Order Create Successfully";
    }

    public OrderDTO getOrderById(int orderId){
        Order order = orderRepo.findById(orderId).orElse(null);
        return modelMapper.map(order,OrderDTO.class);

    }

    public String updateOrderStatus(int orderId, Order order){
        Order orderData = orderRepo.findById(orderId).orElse(null);
        if (orderData == null){
            return "Order not found";
        }
        orderData.setStatus(order.getStatus());

        orderRepo.save(orderData);
        return "Order Updated Successfully";
    }

    public List<Order> getOrdersForUser(int userId){
        return orderRepo.getOrdersByUserId(userId);
    }

    public List<Order> getPastOrdersForUser(int userId){
        return orderRepo.getPastOrdersByUserId(userId);
    }
    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

}
