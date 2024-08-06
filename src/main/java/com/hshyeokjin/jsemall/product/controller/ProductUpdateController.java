package com.hshyeokjin.jsemall.product.controller;


import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.ProductRepository;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productUpdate.do")
public class ProductUpdateController implements BaseController {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Product product = productRepository.findByProductId(productId);
        req.setAttribute("product", product);
        return "/shop/product/product_update_page";
    }
}
