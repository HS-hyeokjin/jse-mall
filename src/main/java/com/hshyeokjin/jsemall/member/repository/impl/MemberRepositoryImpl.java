package com.hshyeokjin.jsemall.member.repository.impl;

import com.hshyeokjin.jsemall.common.util.DbConnection;
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
        String sql = "INSERT INTO member (email, name, password, birth, roleId, point, createAt) VALUES (?, ?, ?, ?, 1, 10000, now())";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, memberSignUpRequest.getEmail());
            pstmt.setString(2, memberSignUpRequest.getName());
            pstmt.setString(3, memberSignUpRequest.getPassword());
            pstmt.setDate(4, Date.valueOf(memberSignUpRequest.getBirth()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("회원 등록에 실패했습니다. No rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("회원 등록에 실패했습니다. 생성된 키를 가져올 수 없습니다.");
                }
            }
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
            Connection connection = DbConnection.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, memberLoginRequest.getEmail());
            pstmt.setString(2, memberLoginRequest.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int memberId = rs.getInt("memberIo");
                int roleId = rs.getInt("roleId");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");
                Date birth = rs.getDate("birth");
                int point = rs.getInt("point");
                Date createdAt = rs.getDate("createdAt");
                Member member = new Member(memberId, roleId,email, name, password, birth, point, createdAt);
                return Optional.of(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
