<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h2>게시글 관리</h2>

	<a>${login.id} 님의 게시글관리 페이지입니다.</a> <br><br>
	
	<a>게시글</a>
	<a href="boardread.do" >0</a><br>
	
	<a>댓글</a>
	<a href="boardread.do" >0</a><br>

</body>
</html>