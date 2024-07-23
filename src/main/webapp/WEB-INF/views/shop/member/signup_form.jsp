<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<style>
    .form-input {
        margin-bottom: 10px;
    }
</style>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/sign-up-post.do">

            <h1 class="h3 mb-3 fw-normal">Please sign up</h1>

           <div class="form-floating form-input">
                <input type="text" name="member_email" class="form-control" id="member_email" placeholder="회원 아이디" required>
                <label for="member_email">회원이메일</label>
            </div>

            <div class="form-floating form-input">
                <input type="password" name="member_password" class="form-control" id="member_password" placeholder="비밀번호" required>
                <label for="member_password">비밀번호</label>
            </div>

            <div class="form-floating form-input">
                <input type="text" name="member_name" class="form-control" id="member_name" placeholder="이름" required>
                <label for="member_name">이름</label>
            </div>

            <div class="form-floating form-input">
                <input type="date" name="member_birth" class="form-control" id="member_birth" placeholder="생일" required>
                <label for="member_birth">생년월일</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">완료</button>

            <p class="mt-5 mb-3 text-muted">© 2024-2026</p>

        </form>
    </div>
</div>