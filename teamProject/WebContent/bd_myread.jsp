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
<h1>마이게시글</h1>

	번호: <input name="num" value="${dto.num}" readonly><p>
	작성자: <input name="num" value="${dto.id}" readonly><p>
	제목: <input name="num" value="${dto.title}" required><p>

	내용:<textarea rows="5" name="content" required>${dto.content}</textarea><p>
	</p>
	
		<input type="button" onclick="location.href='bd_replyui.do?num=${dto.num}'"
		value="댓글추가">			
		<input type="button" onclick="location.href='bd_updateui.do?num=${dto.num}'"
			value="수정">
		<input type="button" onclick="location.href='bd_delete.do?num=${dto.num}'"
			value="삭제"><p>

			
		<input type="button" onclick="location.href='bd_list.do'" value="게시글 목록">

		
		

</body>
</html>