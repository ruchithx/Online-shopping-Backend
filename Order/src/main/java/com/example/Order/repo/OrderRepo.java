package com.example.Order.repo;

import com.example.Order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> getOrdersByUserId(Integer userId);

    @Query(value = "SELECT * FROM orders WHERE orders.user_id = ?1 AND status = 'Completed'",nativeQuery = true)
    List<Order> getPastOrdersByUserId(Integer userId);
}
