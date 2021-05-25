<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function noticeInsert() {
		let title = document.getElementById("title").value;
		let content = document.getElementById("content").value;
		
		frm.title.value=title;
		frm.content.value=content;
		frm.submit();
	}
</script>
<div align="center">
	<h3>공지사항 등록</h3>
	<form id="frm" action="noticeInsert.do" method="post">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
	</form>
	<hr>
	<div style="width: 80%;">
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input id="title" type="text" size=87></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="content" rows="6" cols="90"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="button" onclick="noticeInsert()">등록</button>
		</div>
	</div>
</div>
