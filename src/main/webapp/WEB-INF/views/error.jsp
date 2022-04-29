<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/settings.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<h1>문제 발생</h1>
	<h2>${errorMsg }</h2>
</body>
</html>