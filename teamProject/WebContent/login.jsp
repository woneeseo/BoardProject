<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="login.css">
</head>
<body>   
<div class="login">
  <div class="login-triangle"></div>
  
  <h2 class="login-header">Log in</h2>

  <form action="login.do" method="post" class="login-container">
    <p><input name="id"  placeholder="아이디를 입력해주세요"></p>
    <p><input name="pw" type="password" placeholder="비밀번호를 입력해주세요"></p>
    <p><input type="submit" value="로그인"></p>
    <p><a href="insertui.do">회원가입</a></p>
    <p><a href="findui.do">아이디 / 비밀번호 찾기</a></p>
    <p><a href="main.do">메인으로 돌아가기</a></p>
    
  </form>
</div>

</body>
</html>