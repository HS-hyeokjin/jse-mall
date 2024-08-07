<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 등록</title>
</head>
<body>

<div class="container mt-4">
    <div class="card shadow">
        <div class="card-body">
            <h2 class="card-title text-center mb-4">상품 등록</h2>

            <form action="/admin/registerProduct.do" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="productId" class="form-label">상품ID</label>
                    <input type="text" class="form-control" id="productId" name="productId" required>
                </div>
                <div class="mb-3">
                    <label for="modelName" class="form-label">상품 이름</label>
                    <input type="text" class="form-control" id="modelName" name="modelName" required>
                </div>

                <div class="mb-3">
                    <label for="modelNumber" class="form-label">모델 번호</label>
                    <input type="text" class="form-control" id="modelNumber" name="modelNumber" required>
                </div>

                <div class="mb-3">
                    <label for="categoryId" class="form-label">카테고리</label>
                    <select class="form-select" id="categoryId" name="categoryId" required>
                        <option value="1">스마트폰</option>
                        <option value="2">태블릿</option>
                        <option value="3">노트북</option>
                        <option value="4">워치</option>
                        <option value="5">버즈</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="unitCost" class="form-label">가격</label>
                    <input type="text" class="form-control" id="unitCost" name="unitCost" required>
                </div>

                <div class="mb-3">
                    <label for="productImage" class="form-label">상품 이미지</label>
                    <input type="file" class="form-control" id="productImage" name="productImage" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">상세 설명</label>
                    <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
