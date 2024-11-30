package com.product.product.repo;


import com.product.product.model.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<ProductMedia, Long> {
}
