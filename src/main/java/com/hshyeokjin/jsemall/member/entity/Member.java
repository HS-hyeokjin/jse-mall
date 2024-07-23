package com.hshyeokjin.jsemall.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Member {

    private int memberNo;

    private String email;

    private String name;

    private String password;

    private Auth auth;

    private Date birth;

    private int point;

    private Date createAt;

}
