<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script src="//cdn.ckeditor.com/4.16.1/full/ckeditor.js"></script>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
	<style>
		table {
			border-collapse: collapse;
		}
		th, td {
			text-align: center;
			padding: 10px;
		}
		#cke_content {margin:auto;}
		
	</style>
	<script>
		$(function() {
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload',
				height: '600px',
				width: '800px'
			});
		});
		
		$('#btnUpdate').click(function(e) {
			e.preventDefault();
			console.log(CKEDITOR.instances.content.getData());
			
			let id = $('#id').val();
			let title = $('#title').val();
			let content = CKEDITOR.instances.content.getData();
			$.ajax({
				url: 'ajaxBulletinUpdate',
				data: {
					id: id,
					title: title,
					content: content
				},
				type: 'post',
				success: function(data) {
					console.log(data);
				},
				error: function(err) {
					console.error(err);
				}
			});
		});
		
		function bulletinDelete() {
			frmDel.submit();
		}
	</script>
</head>
<body>
<div align="center">
	<h3 class="font">게시글 내용보기</h3>
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
						<td>${bulletin.hit+1 }</td>
				</tr>

				<tr>
						<th>제목</th>
							<c:if test="${id eq bulletin.writer}">
								<td colspan="7"><input id="title" name="title" type="text" value="${bulletin.title}" size="109"></td>
							</c:if>
							<c:if test="${id ne bulletin.writer}">
								<td colspan="7">${bulletin.title}</td>
							</c:if>
				</tr>
				<tr>
						<th>내용</th>
							<c:if test="${id eq bulletin.writer}">
								<td colspan="7"><textarea id="content" name="content" rows="6" cols="90" class="align-center">${bulletin.content}</textarea></td>
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
			</div>
	</div>
	</form>
	<button type="button" onclick="location.href='bulletinList.do'">목록보기</button>
</div>
</body>
</html>