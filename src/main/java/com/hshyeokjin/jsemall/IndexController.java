package com.hshyeokjin.jsemall;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.controller.BaseController;
import com.hshyeokjin.jsemall.common.paging.Page;
import com.hshyeokjin.jsemall.product.entity.Product;
import com.hshyeokjin.jsemall.product.repository.impl.ProductRepositoryImpl;
import com.hshyeokjin.jsemall.product.service.ProductService;
import com.hshyeokjin.jsemall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


@RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do"})
public class IndexController implements BaseController {
    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int currentPage = 1;
        int pageSize = 9;
        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
            }
        }
        List<Product> allProducts = productService.getAllProducts();
        Page<Product> total = new Page<>(allProducts, allProducts.size());
        Page<Product> productList = productService.getProductsByPage(currentPage,pageSize);
        List <Product> pagingProductList = productList.getContent();
        int totalPages = (int) Math.ceil((double) total.getTotalCount() / pageSize);

        req.setAttribute("pagingProductList", pagingProductList);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        return "shop/main/index";
    }

}