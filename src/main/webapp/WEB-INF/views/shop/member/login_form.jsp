<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"  %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/login-post.do">

            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <input type="text" name="member_email" class="form-control" id="member_email" placeholder="회원 아이디" required>
                <label for="member_email">회원아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="member_password" class="form-control" id="member_password" placeholder="비밀번호" required>
                <label for="member_password">비밀번호</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>