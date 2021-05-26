<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/3.6.0/css/animsition.min.css"
		integrity="sha512-WMC7gu6rcsKhwrVEL6xKXEtRItN/49EjOf3vZI5k6o05KIKRPKrXqVWbxpbwjn9kDdWlTFUhg8kVYhqkthx6XA=="
		crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/3.6.0/js/jquery.animsition.min.js"
		integrity="sha512-zrtSmLMQB7xGl4nJTlebIFmABc8gbEVKndWZbIe5YVFgpPOz+gzisRWox3iiSYGgCdkTEk+QycY3usIYySU0wA=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="main.do">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<li class="nav-item"><a class="nav-link" href="noticeListPaging.do">공지사항</a></li>

				<li class="nav-item"><a class="nav-link" href="bulletinList.do">자유게시판</a></li>

				<li class="nav-item"><a class="nav-link" href="#">소개하는 글</a></li>

				<c:if test="${not empty id }">
					<li class="nav-item"><a class="nav-link" href="memberLoginOut.do">로그아웃</a></li>
				</c:if>

				<c:if test="${empty id }">
					<li class="nav-item"><a class="nav-link" href="memberLoginForm.do">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="memberJoinForm.do">회원가입</a></li>
				</c:if>
				<c:if test="${empty id }">
					<li class="nav-item"><a class="nav-link">📢 Guest 님</a></li>
				</c:if>
				<c:if test="${not empty id }">
					<li class="nav-item"><a class="nav-link">📢 ${id } 님</a></li>
				</c:if>
			</ul>
		</div>
	</nav>
</body>

</html>