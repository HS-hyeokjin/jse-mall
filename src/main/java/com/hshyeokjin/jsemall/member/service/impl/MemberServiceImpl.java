package com.hshyeokjin.jsemall.member.service.impl;

import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;
import com.hshyeokjin.jsemall.member.exception.MemberNotFoundException;
import com.hshyeokjin.jsemall.member.repository.MemberRepository;
import com.hshyeokjin.jsemall.member.service.MemberService;

import java.time.LocalDateTime;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void saveMember(MemberSignUpRequest memberSignUpRequest) {
        memberRepository.save(memberSignUpRequest);
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

