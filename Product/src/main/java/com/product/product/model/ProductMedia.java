package com.product.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productMediaId;
    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
