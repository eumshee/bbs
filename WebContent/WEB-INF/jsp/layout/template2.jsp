<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>
		<tiles:getAsString name="title"/>
	</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/styles.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="${pageContext.request.contextPath }/bootstrap/js/scripts.js"></script>
  	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">	
  	<style>
    .fakeimg {
      height: 200px;
      background: #aaa;
    }
    body { font-family: 'Montserrat', sans-serif; }
  </style>
</head>
<body>
	<tiles:insertAttribute name="head">
	</tiles:insertAttribute>

	<tiles:insertAttribute name="menu">
	</tiles:insertAttribute>
	
	<hr>
	<tiles:insertAttribute name="body">
	</tiles:insertAttribute>

	<hr>
	<tiles:insertAttribute name="foot">
	</tiles:insertAttribute>
	
</body>
<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
<script src="${pageContext.request.contextPath }/bootstrap/js/scripts.js"></script>
</html>