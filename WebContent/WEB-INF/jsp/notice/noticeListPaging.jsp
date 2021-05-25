<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>notice/noticeList.jsp</title>
	<style>
		table {
			border-collapse: collapse;
		}
		th, td {
			text-align: center;
			padding: 10px;
		}
		.pagination {
		  display: inline-block;
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		  transition: background-color .3s;
		  border: 1px solid #ddd;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		  border: 1px solid #4CAF50;
		}
		
		.pagination a:hover:not(.active) {background-color: #ddd;}
	</style>
	<script>
		function formSubmit(id) {
			frm.id.value = id;
			frm.submit();
		}
		
		function formSearch() {
			let search = document.getElementById("search").value;
			
			frmSearch.text.value=search;
			frm.submit();
		}
		
		function goPage(page) {
			location.href="noticeListPaging.do?page="+page;
		}
	</script>
</head>
<body>
	<div align="center">
		<h1>공지사항 리스트(Paging)</h1>
		<form id="frm" action="notice.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<form id="frmSearch" action="noticeSearch.do" method="post">
			<input type="hidden" id="text" name="text">
		</form>
		<hr>
		<div style="width: 80%">
			<table class="table">
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${noticeList }" var="vo">
					<tr onclick="formSubmit(${vo.id})">
						<td width="100">${vo.id }</td>
						<td width="200">${vo.title }</td>
						<td width="150">${vo.regDate }</td>
						<td width="100">${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<input type="text" id="search">
				<button type="button" onclick="formSearch()">검색</button>
				<button type="button" onclick="location.href='main.do'">홈</button>
				<c:if test="${id eq 'admin' }">
					<button type="button" onclick="location.href='noticeForm.do'">등록</button>
				</c:if>
			</div>
			<div class="pagination">
			    <a href="javascript:goPage(${paging.firstPageNo})" class="first">처음 페이지</a>
			    <a href="javascript:goPage(${paging.prevPageNo})" class="prev">이전 페이지</a>
			    <span>
			        <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
			            <c:choose>
			                <c:when test="${i eq paging.pageNo}"><a href="javascript:goPage(${i})" class="active">${i}</a></c:when>
			                <c:otherwise><a href="javascript:goPage(${i})">${i}</a></c:otherwise>
			            </c:choose>
			        </c:forEach>
			    </span>
			    <a href="javascript:goPage(${paging.nextPageNo})" class="next">다음 페이지</a>
			    <a href="javascript:goPage(${paging.finalPageNo})" class="last">마지막 페이지</a>
			</div>
		</div>
	</div>
</body>
</html>