package com.hshyeokjin.jsemall.product.controller;


import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import com.hshyeokjin.jsemall.product.service.ProductService;
import com.hshyeokjin.jsemall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productDelete.do")
public class ProductDeleteController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        productService.deleteProduct(productId);
        return "redirect:/admin/admin.do";
    }

}
