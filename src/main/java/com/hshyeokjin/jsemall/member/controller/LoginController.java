package com.hshyeokjin.jsemall.member.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET,value = "/login.do")
public class LoginController implements BaseController {

    @Override
    public String execute(HttpServletRequest reqests, HttpServletResponse response) {
        HttpSession session = reqests.getSession(false);
        if (session != null && session.getAttribute("member") != null) {
            return "redirect:/index.do";
        }
        return "shop/login/login_form";
    }
}
