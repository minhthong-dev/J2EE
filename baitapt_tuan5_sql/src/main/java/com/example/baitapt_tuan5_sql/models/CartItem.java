package com.example.baitapt_tuan5_sql.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    private product product;
    private int quantity;

    public CartItem() {}

    public CartItem(product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public product getProduct() { return product; }
    public void setProduct(product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Double getTotalPrice() { return product.getPrice() * quantity; }
}
