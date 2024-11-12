package com.product.product.model;

import jakarta.persistence.*;

@Entity
public class ProductTag {

    @Id
    @GeneratedValue(strategy  = GenerationType.AUTO)
    private Long productTagId;


    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;


}
