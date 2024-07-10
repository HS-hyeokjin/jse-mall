package com.hshyeokjin.jsemall.member.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/sign-up.do")
public class SignUpController implements BaseController {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("member") != null) {
            return "redirect:/index.do";
        }
        return "shop/signup/signup_form";
    }
}
