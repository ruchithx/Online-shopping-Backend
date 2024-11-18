package com.product.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductMedia {

    @Id
    private Long productMediaId;
    private String mediaUrl;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
