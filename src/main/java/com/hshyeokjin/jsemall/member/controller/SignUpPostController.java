package com.hshyeokjin.jsemall.member.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.member.entity.Auth;
import com.hshyeokjin.jsemall.member.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signup-post.do")
public class SignUpPostController implements BaseController {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String memberId = request.getParameter("member_id");
        String memberName = request.getParameter("member_name");
        String password = request.getParameter("member_password");
        String memberBirth = request.getParameter("member_birth");
        int gender = Integer.parseInt(request.getParameter("member_gender"));

        Member member = new Member(memberId, memberName, password, Auth.USER , gender, 10000, LocalDateTime.now());

        return "";
    }
}
