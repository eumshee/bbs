<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>product/cartList.jsp</title>
	<style>
		th, td {
			text-align: center;
		}
	</style>
</head>
<body>
	<div align="center">
		<div style="width: 50%;">
			<table class="table">
				<tr>
					<th>유저</th>
					<th>상품코드</th>
					<th>상품이름</th>
					<th>주문개수</th>
					<th>상품가격</th>
					<th>상품총가격</th>
				</tr>
				<c:forEach items="${clist }" var="vo">
					<c:if test="${vo.salePrice ne 0}">
						<tr>
							<td>${vo.userId }</td>
							<td>${vo.itemCode }</td>
							<td>${vo.itemName }</td>
							<td>${vo.itemQty }</td>
							<c:choose>
								<c:when test="${vo.sale eq 'Y' }">
									<td>
										<fmt:formatNumber type="currency">${vo.salePrice }</fmt:formatNumber>
									</td>
									<td>
										<fmt:formatNumber type="currency">${vo.psum }</fmt:formatNumber>
									</td>
								</c:when>
								<c:otherwise>								
									<td>
										<fmt:formatNumber type="currency">${vo.price }</fmt:formatNumber>
									</td>
									<td>
										<fmt:formatNumber type="currency">${vo.sum }</fmt:formatNumber>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:if>
					<c:if test="${empty vo.itemCode and empty vo.itemName and vo.price eq 0 and empty vo.sale and vo.salePrice eq 0}">
						<tr>
							<th colspan="3">합계</th>
							<td>${vo.itemQty }</td>
							<td></td>
							<td></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>	
		</div>
	</div>
</body>
</html>