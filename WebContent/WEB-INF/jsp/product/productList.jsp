<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section class="py-5">
<div class="container px-4 px-lg-5 mt-5">
	<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
		<!-- 첫번째 데이터(샘플) -->
		<div class="col mb-5">
			<div class="card h-100">
				<!-- Product image-->
				<img class="card-img-top"
					src="upload/random.png" alt="..." />
				<!-- Product details-->
				<div class="card-body p-4">
					<div class="text-center">
						<!-- Product name-->
						<h5 class="fw-bolder">Fancy Product</h5>
						<!-- Product price-->
						<fmt:formatNumber type="currency">1000</fmt:formatNumber>
						&nbsp;-&nbsp;
						<fmt:formatNumber type="currency">10000</fmt:formatNumber>
					</div>
				</div>
				<!-- Product actions-->
				<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					<div class="text-center">
						<a class="btn btn-outline-dark mt-auto" href="#">View options</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 두번째 데이터 -->
		<c:forEach items="${list }" var="vo">
		<div class="col mb-5">
			<div class="card h-100">
				<c:if test="${vo.sale eq 'Y' }">
					<!-- Sale badge-->
					<div class="badge bg-dark text-white position-absolute"
						style="top: 0.5rem; right: 0.5rem">Sale</div>
				</c:if>
				<!-- Product image-->
				<img class="card-img-top"
					src="upload/${vo.itemImage }" alt="..." />
				<!-- Product details-->
				<div class="card-body p-4">
					<div class="text-center">
						<!-- Product name-->
						<h5 class="fw-bolder">${vo.itemName }</h5>
						<!-- Product reviews-->
							<div class="d-flex justify-content-center small text-warning mb-2">
								<c:forEach begin="1" end="${vo.likeIt }">
								<div class="bi-star-fill"></div>
								</c:forEach>
							</div>
						<c:choose>
							<c:when test="${vo.sale eq 'Y' }">
								<!-- Product price-->
								<span class="text-muted text-decoration-line-through">
								<fmt:formatNumber type="currency" value="${vo.price }"></fmt:formatNumber>
								</span>&nbsp;
								<fmt:formatNumber type="currency" value="${vo.salePrice }"></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								<!-- Product price-->
								<span>
								<fmt:formatNumber type="currency" value="${vo.price }"></fmt:formatNumber>
								</span>
							</c:otherwise>						
						</c:choose>
					</div>
				</div>
				<!-- Product actions-->
				<c:if test="${!empty id }">
				<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					<div class="text-center">
						<a class="btn btn-outline-dark mt-auto" onclick="addCnt('${vo.itemCode}')">Add to cart</a>
					</div>
				</div>
				</c:if>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
</section>

<script>
	function addCnt(itemCode) {
	 //e.preventDefault();
	 $.ajax({
		 url: '${pageContext.request.contextPath}/addCart.do',
		 data: {id: '${id }',
			 itemCode: itemCode
		 },
		 success: function(data) {
			 console.log(data);
			 location.href = 'productList.do';
		 },
		 error: function(err) {
			 console.error(err);
		 }
	 });
 }
</script>