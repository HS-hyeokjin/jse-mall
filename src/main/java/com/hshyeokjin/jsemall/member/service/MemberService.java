package com.hshyeokjin.jsemall.member.service;

import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;

public interface MemberService {

    void saveMember(MemberSignUpRequest memberSignUpRequest);

    Member loginMember(MemberLoginRequest memberLoginRequest);

}
