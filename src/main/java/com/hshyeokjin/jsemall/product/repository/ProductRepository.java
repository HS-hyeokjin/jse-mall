package com.hshyeokjin.jsemall.product.repository;

import com.hshyeokjin.jsemall.common.paging.Page;
import com.hshyeokjin.jsemall.product.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product findByProductId(int productId);

    int save(Product product);

    int update(Product product);

    int deleteByProductId(int productId);

    int countByProductId(int productId);

    List<Product> findAll();

    Page<Product> findProductsByPage(int page, int pageSize);
}
