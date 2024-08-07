package com.hshyeokjin.jsemall.product.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private int productId;
    private int categoryId;
    private String name;
    private int price;
    private String description;
    private String image;

    public Product(int productId, int categoryId, String name, int price, String description, String image) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}
