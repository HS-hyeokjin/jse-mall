<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${pagingProductList}">
        <div class="col">
            <div class="card shadow-sm">
                <a href="<c:url value='/product/productDetail.do'/>?productId=${product.getProductId()}">
                    <img src="/resources/${product.getImage()}" class="bd-placeholder-img card-img-top" width="50px" height="320px" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                </a>
                <div class="card-body">
                    <h5 class="card-title">${product.getName()}</h5>
                    <div>&ensp;</div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <a href="<c:url value='/product/productDetail.do'/>?productId=${product.getProductId()}" class="btn btn-sm btn-outline-secondary">View</a>
                            <a href="<c:url value='/admin/productUpdate.do'/>?productId=${product.getProductId()}" class="btn btn-sm btn-outline-secondary">Edit</a>
                        </div>
                        <small class="text-muted">${product.getPrice()}</small>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="d-flex justify-content-center mt-3">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="page">
                <c:choose>
                    <c:when test="${page eq currentPage}">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${page}<span class="sr-only"></span></span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="?page=${page}">${page}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </nav>
</div>
