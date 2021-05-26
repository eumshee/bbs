<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		table {
			border-collapse: collapse;
		}
		th, td {
			text-align: center;
			padding: 10px;
		}
	</style>
	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
	<script>
		$(function() {
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload',
				height: '600px',
				width: '800px'
			});
		});
		
		function bulletinDelete() {
			frmDel.submit();
		}
	</script>
</head>
<body>
<div align="center">
	<h3>게시글 내용보기</h3>
	<hr>
	<form id="frmDel" action="bulletinDelete.do" method="post">
		<input type="hidden" id="writer" name="writer" value="${id }">
		<input type="hidden" id="id" name="id" value="${bulletin.id}">
	</form>
	<form id="frm" action="bulletinUpdate.do" method="post">
		<input type="hidden" name="writer" value="${id }">
		<input type="hidden" name="id" value="${bulletin.id}">		

	<div style="width: 80%;">
			<table class="table">
				<tr>
						<th>순번</th>
						<td>${bulletin.id}</td>
						<th>작성일자</th>
						<td>${bulletin.regDate}</td>
						<th>작성자</th>
						<td>${bulletin.writer}</td>
						<th>조회수</th>
						<td>${bulletin.hit+1}</td>
				</tr>

				<tr>
						<th>제목</th>
							<c:if test="${id eq bulletin.writer}">
								<td colspan="7"><input id="title" name="title" type="text" value="${bulletin.title}" size="88"></td>
							</c:if>
							<c:if test="${id ne bulletin.writer}">
								<td colspan="7">${bulletin.title}</td>
							</c:if>
				</tr>
				<tr>
						<th>내용</th>
							<c:if test="${id eq bulletin.writer}">
								<td colspan="7"><textarea id="content" name="content" rows="6" cols="90">${bulletin.content}</textarea></td>
							</c:if>
							<c:if test="${id ne bulletin.writer}">
								<td colspan="7">${bulletin.content}</td>
							</c:if>
				</tr>
			</table>
			<div align="right">
				<c:if test="${id eq bulletin.writer}">
					<button type="submit">수정</button>
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
	</form>
	<button type="button" onclick="location.href='bulletinList.do'">목록보기</button>
</div>
</body>
</html>