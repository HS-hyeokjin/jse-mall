package com.hshyeokjin.jsemall.product.controller;


import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.controller.BaseController;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.ProductRepository;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import com.hshyeokjin.jsemall.product.service.ProductService;
import com.hshyeokjin.jsemall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/product/productDetail.do")
public class ProductDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));

        Product product = productService.getProduct(productId);

        req.setAttribute("product", product);

        return "shop/product/product_detail_page";
    }
}
