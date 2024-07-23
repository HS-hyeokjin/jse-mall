package com.hshyeokjin.jsemall.member.controller;

import com.hshyeokjin.jsemall.common.annotation.RequestMapping;
import com.hshyeokjin.jsemall.common.contoller.BaseController;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;
import com.hshyeokjin.jsemall.member.repository.impl.MemberRepositoryImpl;
import com.hshyeokjin.jsemall.member.service.MemberService;
import com.hshyeokjin.jsemall.member.service.impl.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/sign-up-post.do")
public class SignUpPostController implements BaseController {

    private final MemberService memberService = new MemberServiceImpl(new MemberRepositoryImpl());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("member_email");
        String name = request.getParameter("member_name");
        String password = request.getParameter("member_password");
        LocalDate birth = LocalDate.parse(request.getParameter("member_birth"));

        MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest();
        memberSignUpRequest.setEmail(email);
        memberSignUpRequest.setName(name);
        memberSignUpRequest.setPassword(password);
        memberSignUpRequest.setBirth(birth);

        memberService.saveMember(memberSignUpRequest);
        log.debug("유저 회원가입 : {}", memberSignUpRequest);

        return "redirect:/index.do";
    }
}
