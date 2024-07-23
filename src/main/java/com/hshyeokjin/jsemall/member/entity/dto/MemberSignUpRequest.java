package com.hshyeokjin.jsemall.member.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberSignUpRequest {

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

}
