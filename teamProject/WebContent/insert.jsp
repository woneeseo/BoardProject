<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="insert.css" rel="stylesheet">
</head>
<body>

	<form action="insert.do" method="post" id="signup">
		<h1>You can Quickly and easily sign up with us!</h1>
		아이디: <input name="id" placeholder="아이디를 입력하세요." required/>
		<button id="select">중복검사</button><span></span><br>
		이름: <input name="name" placeholder="이름을 입력하세요." required/><br>
		e-mail: <input type="email" name="email" placeholder="이메일을 입력하세요." required/><br>
		주소: <input type="text" id="address" onclick="goPopup()" name="address" required="required"> <br>
		비밀번호: <input type="password" name="pw" placeholder="비밀번호를 입력하세요." required/><br>		
		생년월일: <input type="date" name="birth" required/><br>
		전화번호: <input type="tel" name="tel" placeholder="전화번호를 입력하세요" required/><br>
		<input type="submit" value="등록" class="button"><br>
		<br>
		<h4>이미 아이디가 있으시다면?</h4>
		<button class="button" onclick="location.href='loginui.do'">로그인</button>
  </form>
  <script type="text/javascript">
  function goPopup(){
		
		var pop = window.open("/teamProject/jusoPopup.jsp","pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");  
	}
	function jusoCallBack(roadFullAddr){
			var addressEI = document.querySelector("#address")
			addressEI.value = roadFullAddr;
			
	}
  
  $(document).ready(function(){
  
  	$("#select").click(function(event) {
		event.preventDefault();
	   
		var idv = $("input[name='id']").val();
		
		$.ajax({
			type: 'get',
			url: 'idcheck.do',
			data: {
				id : idv
			},
			dataType: 'text',
			success: function(result) {
				console.log(result);
				$("span").text(result);
			},
			error: function(request, status, error) {
				console.log(error);
			},
			
		});
 	 });
  });
  </script>
  

</body>
</html>