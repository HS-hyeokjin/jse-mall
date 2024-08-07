package com.hshyeokjin.jsemall.member.repository;

import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;

import java.util.Optional;

public interface MemberRepository {

    int save(MemberSignUpRequest memberSignUpRequest);

    Optional<Member> findByEmailAndPassword(MemberLoginRequest memberLoginRequest);
}
