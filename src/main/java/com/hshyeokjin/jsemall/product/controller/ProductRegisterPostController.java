package com.hshyeokjin.jsemall.product.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import com.hshyeokjin.jsemall.product.service.ProductService;
import com.hshyeokjin.jsemall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/registerProduct.do")
public class ProductRegisterPostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part filePart = req.getPart("productImage");
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            String uploadDirectory = req.getServletContext().getRealPath("/resources");

            Path filePath = Paths.get(uploadDirectory, fileName);
            log.debug("filePath : {}",filePath);

            try (var fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, filePath);
                log.debug("filePath2 : {}",filePath);
            }

            int productId = Integer.parseInt(req.getParameter("productId"));
            String productName = req.getParameter("modelName");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String modelNumber = req.getParameter("modelNumber");
            double unitCost = Double.parseDouble(req.getParameter("unitCost"));
            String productImage = fileName;
            String description = req.getParameter("description");

            Product product = new Product(productId, categoryId, modelNumber, productName, productImage, unitCost, description);
            log.debug("이미지{} " ,product.getProductImg());
            productService.saveProduct(product);

            return "redirect:/admin/admin.do";
        } catch (NumberFormatException | IOException | ServletException e) {
            log.error("Error processing product registration", e);
            return "error-page";
        }
    }
}
