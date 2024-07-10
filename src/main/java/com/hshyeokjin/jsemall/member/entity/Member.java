package com.hshyeokjin.jsemall.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {
    public enum Auth{
        ADMIN, USER
    }

    private int memberId;
    private String memberName;
    private String password;
    private Auth auth;
    private int gender;
    private int point;
    private LocalDateTime createAt;

}
