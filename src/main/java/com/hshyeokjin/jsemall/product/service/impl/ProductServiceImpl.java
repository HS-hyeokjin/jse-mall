package com.hshyeokjin.jsemall.product.service.impl;

import com.hshyeokjin.jsemall.common.paging.Page;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.ProductRepository;
import com.hshyeokjin.jsemall.product.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public void saveProduct(Product product) {
        if (productRepository.countByProductId(product.getProductId()) == 0) {
            productRepository.save(product);

        } else {
            throw new RuntimeException("Product already exists with id: " + product.getProductId());
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.countByProductId(product.getProductId()) == 1) {
            productRepository.update(product);
        } else {
            throw new RuntimeException("Product not found with id: " + product.getProductId());
        }
    }

    @Override
    public void deleteProduct(int productId) {
        if (productRepository.countByProductId(productId) == 1) {
            productRepository.deleteByProductId(productId);
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductsByPage(int page, int pageSize) {
        return productRepository.findProductsByPage(page, pageSize);
    }
}
