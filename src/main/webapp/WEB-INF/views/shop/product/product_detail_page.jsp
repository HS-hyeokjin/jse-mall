<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>상품 상세</title>
</head>
<body>

<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-dark text-secondary">
            <h5 class="mb-0">상품 상세</h5>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <img src="/resources/${product.getImage()}" class="img-fluid" alt="Product Image">
                </div>
                <div class="col-md-6">
                    <h4 class="text-primary">${product.getName()}</h4>
                    <p class="lead"> ${product.getPrice()} 원</p>
                    <p class="mb-0">Description: ${product.getDescription()}</p>
                    <div class="mt-3">
                        <button type="button" class="btn btn-primary" >구매하기</button>
                        <a href="<c:url value='/cartItemAdd.do'/>?productId=${product.getProductId()}" class="btn btn-success">장바구니에 추가</a>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
