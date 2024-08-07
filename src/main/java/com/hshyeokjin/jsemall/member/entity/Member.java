package com.hshyeokjin.jsemall.member.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Member {
    private int memberId;
    private int roleId;
    private String email;
    private String name;
    private String password;
    private Date birth;
    private int point;
    private Date createAt;

    public Member(int memberId, int roleId, String email, String name, String password, Date birth,  int point, Date createAt) {
        this.memberId = memberId;
        this.roleId = roleId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.point = point;
        this.createAt = createAt;
    }
}
