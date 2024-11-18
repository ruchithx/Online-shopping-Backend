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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }


}
