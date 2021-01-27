<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="find.css">
</head>
<body>

<div class="find">
	<div class="find-riangle"></div>
<h2 class="find-header">아이디 찾기</h2>
   <form action="findid.do" method="post" class="find-container">
   <p>이름 <input type="text" name="name" placeholder="이름" required></p>
   <p>이메일 <input type="text" name="email" placeholder="이메일" required></p>
   <p><input type="submit" value="확인"></p>
</form>

           <h2 class="find-header2">비밀번호 찾기</h2>
            <form action="findpw.do" method="post" class="find-container">
               <p>이름<input type="text" name="name" placeholder="이름" required></p>
               <p>이메일<input type="text" name="email" placeholder=" 이메일" required></p>
        	<p><input type="submit" value="확인" ></p>
        </form>
</div>
</body>
</html>