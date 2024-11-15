package com.example.cart.cart;

public class CartItem {
    private Long itemId;
    private Long cartId;
    private Long productId;
    private Long productName;
    private Long productDescription;
    private Long quantity;
    private Double price;
    private Double discount;

    public CartItem() {
    }

    public CartItem(Long itemId, Long cartId, Long productId, Long productName, Long productDescription, Long quantity, Double price, Double discount) {
        this.itemId = itemId;
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public CartItem(Long cartId, Long productId, Long productName, Long productDescription, Long quantity, Double price, Double discount) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductName() {
        return productName;
    }

    public void setProductName(Long productName) {
        this.productName = productName;
    }

    public Long getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Long productDescription) {
        this.productDescription = productDescription;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", productName=" + productName +
                ", productDescription=" + productDescription +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
