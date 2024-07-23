package com.hshyeokjin.jsemall.member.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.repository.impl.MemberRepositoryImpl;
import com.hshyeokjin.jsemall.member.service.MemberService;
import com.hshyeokjin.jsemall.member.service.impl.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method=RequestMapping.Method.POST, value="/login-post.do")
public class LoginPostController implements BaseController {

    private MemberService memberService = new MemberServiceImpl(new MemberRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("member_email");
        String password = request.getParameter("member_password");

        MemberLoginRequest memberLoginRequest = new MemberLoginRequest();

        memberLoginRequest.setEmail(email);
        memberLoginRequest.setPassword(password);

        Member member = memberService.loginMember(memberLoginRequest);

        if (member != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("member", member);
            session.setMaxInactiveInterval(60 * 60);
            return "redirect:/index.do";
        }
        return "shop/member/login_form";
    }
}
