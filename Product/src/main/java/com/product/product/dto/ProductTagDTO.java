package com.product.product.dto;

public class ProductTagDTO {

    private Long tagId;
    private Long productId;

    public Long getTagId() {
        return this.tagId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
