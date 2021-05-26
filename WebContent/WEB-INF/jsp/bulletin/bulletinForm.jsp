<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>bulletin/bulletinForm.jsp</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
	<script>
		$(function() { // 모두 불러온 후.
			// ckeditor, 업로드 서블릿
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload',
				height: '600px',
				width: '800px'
			});
		}); 
		
		function formCheck() {
			if(frm.title.value == "") {
				alert("제목을 입력하세요.");
				frm.title.focus();
				return false;
			}
			frm.submit();
		}
	</script>
</head>
<body>
<div align="center">
	<div>
		<h1>게시글등록</h1>
	</div>	
	<hr>	
	<div style="width: 80%;">
		<form id="frm" action="bulletinInsert.do" method="post">
			<input type="hidden" name="id" value="${id }">
			<div>
				<table class="table">
					<tr>
						<th width="150"><label for="title">제목</label></th>
						<td width="300">
							<input type="text" id="title" name="title">
						</td>
					</tr>
					<tr>
						<th width="150"><label for="content">내용</label></th>
						<td width="300">
							<textarea id="content" name="content"></textarea>
						</td>
					</tr>
				</table>
				<div>
					<button type="button" onclick="formCheck()">등록</button>
					<button type="reset">취소</button>
					<button type="button" onclick="location.href='main.do'">홈</button>
				</div>
			</div>
		</form>
	</div>		
</div>
</body>
</html>