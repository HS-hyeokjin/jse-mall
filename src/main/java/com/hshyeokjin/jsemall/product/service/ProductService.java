package com.hshyeokjin.jsemall.product.service;

import com.hshyeokjin.jsemall.common.paging.Page;
import com.hshyeokjin.jsemall.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(int productId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    public List<Product> getAllProducts();

    public Page<Product> getProductsByPage(int page, int pageSize);
}
