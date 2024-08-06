package com.hshyeokjin.jsemall.product.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import com.hshyeokjin.jsemall.product.service.ProductService;
import com.hshyeokjin.jsemall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/updateProduct.do")
public class ProductUpdatePostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int productId = Integer.parseInt(req.getParameter("productId"));
        int categoryId =  Integer.parseInt(req.getParameter("categoryId"));
        String modelName = req.getParameter("modelName");
        String modelNumber = req.getParameter("modelNumber");
        double unitCost = Double.parseDouble(req.getParameter("unitCost"));
        String description = req.getParameter("description");

        Product updatedProduct = new Product(productId, categoryId, modelName, modelNumber, unitCost, description);
        productService.updateProduct(updatedProduct);

        return "redirect:/admin/admin.do";
    }
}
