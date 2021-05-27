<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>notice/noticeList.jsp(paging)</title>
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
			frmSearch.submit();
		}
		
		function formDelete(id) {
			frmDel.id.value = id;
			frmDel.submit();
		}
			
		function goPage(page) {
			location.href="noticeListPaging.do?page="+page;
		}
		
		function noticesDelete() {
			//each로 loop를 돌면서 checkbox의 check된 값을 가져와 담아준다.
			let chkArr = new Array();
			$("input:checkbox[name=chk]:checked").each(function(){
				chkArr.push($(this).val());
			});
			frmsDel.chk.value = chkArr;
			console.log(chkArr);
			frmsDel.submit();
		}
	</script>
</head>
<body>
	<div align="center">
		<h1>공지사항 리스트</h1>
		<form id="frm" action="notice.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<form id="frmSearch" action="noticeSearch.do" method="post">
			<input type="hidden" id="title" name="title">
			<input type="hidden" id="content" name="content">
		</form>
		<form id="frmDel" action="noticeDelete.do" method="post">
			<input type="hidden" id="did" name="did">
		</form>
		<form id="frmsDel" action="noticesDelete.do" method="post">
			<input type="hidden" id="chk" name="chk">
		</form>
		<hr>
		<div style="width: 80%">
			<table class="table">
				<tr>
					<c:if test="${id eq 'admin' }">
					<th>선택</th>
					</c:if>
					<th>순번</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>조회수</th>
					<c:if test="${id eq 'admin' }">
					<th>기능</th>
					</c:if>
				</tr>
				<c:forEach items="${noticeList }" var="vo">
					<tr>
						<c:if test="${id eq 'admin' }">
							<td width="10">
								<input type="checkbox" id="chk" name="chk" value="${vo.id }">
							</td>
						</c:if>
						<td width="100" onclick="formSubmit(${vo.id})">${vo.id }</td>
						<td width="200" onclick="formSubmit(${vo.id})">${vo.title }</td>
						<td width="150" onclick="formSubmit(${vo.id})">${vo.regDate }</td>
						<td width="100" onclick="formSubmit(${vo.id})">${vo.hit }</td>
						<c:if test="${id eq 'admin' }">
							<td width="50">
								<button type="button" onclick="formDelete(${vo.id})">삭제</button>							
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<div>
				<c:if test="${id eq 'admin' }">
					<button type="button" onclick="noticesDelete()">선택삭제</button>
				</c:if>
				<input type="text" id="search" size=35>
				<button type="button" onclick="formSearch()">검색</button>
				<c:if test="${id eq 'admin' }">
					<button type="button" onclick="location.href='noticeForm.do'">등록</button>
				</c:if>
				<br><br>
				<button type="button" onclick="location.href='main.do'">홈</button>
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