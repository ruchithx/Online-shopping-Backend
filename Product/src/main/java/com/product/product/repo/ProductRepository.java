package com.product.product.repo;

import com.product.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.brand = ?1")
    List<Product> findByBrand(String brand);



}
