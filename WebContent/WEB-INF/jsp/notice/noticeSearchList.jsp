<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	table {
		border-collapse: collapse;
	}
	th, td {
		text-align: center;
		padding: 10px;
	}
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
</script>
<div align="center">
		<h1>공지사항 검색 리스트</h1>
		<form id="frm" action="notice.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<form id="frmSearch" action="noticeSearch.do" method="post">
			<input type="hidden" id="title" name="title">
			<input type="hidden" id="content" name="content">
		</form>
		<form id="frmDel" action="noticeDelete.do" method="post">
			<input type="hidden" id="id" name="id">
		</form>
		<hr>
	<div style="width: 80%;">
		<table class="table">
			<tr>		
				<th>순번</th>
				<th>제목</th>
				<th>작성일자</th>
				<th>조회수</th>
				<c:if test="${id eq 'admin' }">
					<th>기능</th>
				</c:if>
			</tr>
			<c:forEach items="${noticeList }" var="vo">
				<tr >
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
			<input type="text" id="search">
			<button type="button" onclick="formSearch()">검색</button>
			<button type="button" onclick="location.href='noticeListPaging.do'">목록보기</button>
			<c:if test="${id eq 'admin' }">
				<button type="button" onclick="location.href='noticeForm.do'">등록</button>
			</c:if>
		</div>
	</div>
</div>