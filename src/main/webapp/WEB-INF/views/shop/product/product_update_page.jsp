<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 수정</title>
</head>
<body>

<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-dark text-white">
            <h5 class="mb-0">상품 수정</h5>
        </div>
        <div class="card-body">
            <form action="/admin/updateProduct.do" method="post">
                <input type="hidden" name="productId" value="${product.productId}">
                <div class="mb-3">
                    <label for="modelName" class="form-label">Model Name</label>
                    <input type="text" class="form-control" id="modelName" name="modelName" value="${product.modelName}" required>
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
                    <label for="modelNumber" class="form-label">Model Number</label>
                    <input type="text" class="form-control" id="modelNumber" name="modelNumber" value="${product.modelNumber}" required>
                </div>

                <div class="mb-3">
                    <label for="unitCost" class="form-label">Unit Cost</label>
                    <input type="number" class="form-control" id="unitCost" name="unitCost" value="${product.unitCost}" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="4" required>${product.description}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">수정 완료</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
