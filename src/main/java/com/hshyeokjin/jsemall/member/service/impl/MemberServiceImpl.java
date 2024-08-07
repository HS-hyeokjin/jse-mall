package com.hshyeokjin.jsemall.member.service.impl;

import com.hshyeokjin.jsemall.cart.repository.CartItemRepository;
import com.hshyeokjin.jsemall.cart.repository.CartRepository;
import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;
import com.hshyeokjin.jsemall.member.exception.MemberNotFoundException;
import com.hshyeokjin.jsemall.member.repository.MemberRepository;
import com.hshyeokjin.jsemall.member.service.MemberService;

import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    public MemberServiceImpl(MemberRepository memberRepository, CartRepository cartRepository) {
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
    }

    public void saveMember(MemberSignUpRequest memberSignUpRequest) {
        int memberId = memberRepository.save(memberSignUpRequest);
        cartRepository.save(memberId);
    }

    @Override
    public Member loginMember(MemberLoginRequest memberLoginRequest) {
        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(memberLoginRequest);

        if (memberOptional.isPresent()) {
            return memberOptional.get();
        }
        throw new MemberNotFoundException("멤버를 찾지 못 함");
    }
}

