package com.example.Order.service;

import com.example.Order.Client.ProductClient;
import com.example.Order.dto.OrderDTO;
import com.example.Order.dto.OrderItemDTO;
import com.example.Order.dto.ProductDTO;
import com.example.Order.model.Order;
import com.example.Order.model.OrderItem;
import com.example.Order.model.OrderItemResponse;
import com.example.Order.model.OrderResponse;
import com.example.Order.repo.OrderRepo;
//import com.example.cart.Client.ProductClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductClient productClient;

//    @Autowired
//    private ProductClient productClient;

    public String createOrder(Order order) {
        if(order.getUserId() == "") {
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
            // Fetch the order from the database
            Order order = orderRepo.findById(orderId)
                    .orElseThrow(() -> new NoSuchElementException("Order with ID " + orderId + " not found"));

            // Fetch the product details for each order item
            int[] productIds = order.getOrderItems().stream()
                    .mapToInt(OrderItem::getProductId)
                    .toArray();

            for (int i = 0; i < productIds.length; i++) {
                System.out.println("++++++++++++++++++++"+ productIds[i] +"+");
            }

//            System.out.println("++++++++++++++++++++"+ productIds);

            // Fetch the product details from the Product service
            List<ProductDTO> products = productClient.getProductById(productIds);
            System.out.println("++++++++++++++++++++"+ products +"+");

            // Map the order items to OrderItemDTO and set the corresponding product details
            List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                    .map(orderItem -> {
                        // Find the corresponding product for this order item
                        ProductDTO productDTO = products.stream()
                                .filter(product -> product.getProductId() == orderItem.getProductId())
                                .findFirst()
                                .orElse(null);  // If no product found, set to null (handle this case appropriately)

                        // Map the OrderItem to OrderItemDTO
                        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);

                        // Set the product details in the OrderItemDTO
                        orderItemDTO.setProduct(productDTO);

                        return orderItemDTO;
                    })
                    .collect(Collectors.toList());

            // Map the entire order to OrderDTO and set the order items as OrderItemDTO
            OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
            orderDTO.setOrderItems(orderItemDTOs);  // Now setting List<OrderItemDTO> with product details

            return orderDTO;
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

    public List<Order> getOrdersForUser(String userId) {
        try {


            return orderRepo.getOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching orders for user ID " + userId + ": " + e.getMessage());
        }
    }



    public List<Order> getPastOrdersForUser(String userId) {
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
