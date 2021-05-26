<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 리스트</title>
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
			frmSearch.title.value=search;
			frmSearch.content.value=search;
			frmSearch.writer.value=search;
			
			frmSearch.submit();
		}
	</script>
</head>
<body>
	<div align="center">
		<h1>게시글 리스트</h1>
		<form id="frm" action="bulletin.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<form id="frmSearch" action="bulletinSearch.do" method="post">
			<input type="hidden" id="title" name="title">
			<input type="hidden" id="content" name="content">
			<input type="hidden" id="writer" name="writer">
		</form>
		<hr>
		<div style="width: 80%">
			<table class="table">
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일자</th>
					<th>조회수</th>
				</tr>
				<c:forEach items="${bulletinList }" var="vo">
					<tr onclick="formSubmit(${vo.id})">
						<td width="100">${vo.id }</td>
						<td width="200">${vo.title }</td>
						<td width="150">${vo.writer }</td>
						<td width="150">${vo.regDate }</td>
						<td width="100">${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<input type="text" id="search">
				<button type="button" onclick="formSearch()">검색</button>
				<button type="button" onclick="location.href='main.do'">홈</button>
				<c:if test="${!empty id}">
					<button type="button" onclick="location.href='bulletinForm.do'">등록</button>
				</c:if>
			</div>
			<br>
			<jsp:include page="../common/paging.jsp" flush="true">
			    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
			    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
			    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
			    <jsp:param name="pageNo" value="${paging.pageNo}" />
			    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
			    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
			    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
			</jsp:include>
		</div>
	</div>
</body>
</html>