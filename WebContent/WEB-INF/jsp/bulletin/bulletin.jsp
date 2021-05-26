<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	function noticeUpdate() {
		let id = document.getElementById("cid").innerHTML;
		let title= document.getElementById("ctitle").value;
		let content = document.getElementById("ccontent").value;
		
		frm.id.value=id;
		frm.title.value=title;
		frm.content.value=content;
		frm.submit();
	}
</script>
<div align="center">
	<h3>게시글 내용보기</h3>
	<form id="frm" action="bulletinUpdate.do" method="post">
		<input type="hidden" name="id">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
	</form>
	<hr>
	<div style="width: 80%;">
			<table class="table">
				<tr>
						<th>순번</th>
						<td id="cid">${bulletin.id}</td>
						<th>작성일자</th>
						<td>${bulletin.regDate}</td>
						<th>작성자</th>
						<td>${bulletin.writer}</td>
						<th>조회수</th>
						<td>${bulletin.hit+1}</td>
				</tr>

				<tr>
						<th>제목</th>
						<td colspan="7"><input id="ctitle" type="text" value="${bulletin.title}" size="88"></td>
				</tr>
				<tr>
						<th>내용</th>
						<td colspan="7"><textarea id="ccontent" rows="6" cols="90">${bulletin.content}</textarea></td>
				</tr>
			</table>
			<div align="right">
				<button type="button" onclick="location.href='bulletinList.do'">목록보기</button>
				<c:if test="${!empty id}">
					<button type="button" onclick="bulletinUpdate()">수정</button>
					<button type="button" onclick="bulletinDelete()">삭제</button>
				</c:if>
				<c:if test="${empty id}">
					<script>
						$('#ctitle').prop('readonly', true);
						$('#ccontent').prop('readonly', true);
					</script>
				</c:if>
			</div>
	</div>
</div>
