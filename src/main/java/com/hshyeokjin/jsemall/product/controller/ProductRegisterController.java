package com.hshyeokjin.jsemall.product.controller;


import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/admin/productRegister.do")
public class ProductRegisterController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "shop/product/product_register_form";
    }
}
