package com.hshyeokjin.jsemall.member.repository.impl;

import com.hshyeokjin.jsemall.common.util.DbConnection;
import com.hshyeokjin.jsemall.member.entity.Auth;
import com.hshyeokjin.jsemall.member.entity.Member;
import com.hshyeokjin.jsemall.member.entity.dto.MemberLoginRequest;
import com.hshyeokjin.jsemall.member.entity.dto.MemberSignUpRequest;
import com.hshyeokjin.jsemall.member.exception.UserRegisterException;
import com.hshyeokjin.jsemall.member.repository.MemberRepository;

import java.sql.*;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository {

    @Override
    public int save(MemberSignUpRequest memberSignUpRequest) {
        String sql = "INSERT INTO member (email, name, password, birth, auth, point, createdAt) VALUES (?, ?, ?, ?, 'USER', 10000, now())";

        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, memberSignUpRequest.getEmail());
            pstmt.setString(2, memberSignUpRequest.getName());
            pstmt.setString(3, memberSignUpRequest.getPassword());
            pstmt.setDate(4, Date.valueOf(memberSignUpRequest.getBirth()));

            return pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("중복된 이메일입니다.", e);
        } catch (SQLException e) {
            throw new UserRegisterException("회원 등록(save) 오류 ", e);
        }
    }

    @Override
    public Optional<Member> findByEmailAndPassword(MemberLoginRequest memberLoginRequest) {
        String sql = "select * from member where email = ? and password = ?";

        try {
            System.out.println(1);
            Connection connection = DbConnection.getConnection();
            System.out.println(1);

            PreparedStatement pstmt = connection.prepareStatement(sql);
            System.out.println(1);
            pstmt.setString(1, memberLoginRequest.getEmail());
            System.out.println(1);
            pstmt.setString(2, memberLoginRequest.getPassword());
            System.out.println(1);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(1);
            if (rs.next()) {
                System.out.println(1);
                int memberNo = rs.getInt("memberNo");
                System.out.println(1);
                String email = rs.getString("email");
                System.out.println(1);
                String name = rs.getString("name");
                System.out.println(1);
                String password = rs.getString("password");
                System.out.println(1);
                Date birth = rs.getDate("birth");
                System.out.println(1);
                Auth auth = Auth.valueOf(rs.getString("auth"));
                System.out.println(1);
                int point = rs.getInt("point");
                System.out.println(1);
                Date createdAt = rs.getDate("createdAt");
                System.out.println(1);

                System.out.println(1);
                Member member = new Member(memberNo, email, name, password, auth, birth, point, createdAt);
                return Optional.of(member);
            }
        } catch (SQLException e) {
            System.out.println("오류");
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
