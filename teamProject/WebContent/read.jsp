<%@page import="kr.co.domain.MemberDAO"%>
<%@page import="kr.co.ezen.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <link href="read.css" type="text/css" rel="stylesheet">
</head>
<body>

<div id="wrapper">

	<div id="header">
		<h1><a href="main.do">My Page</a></h1>
	</div>

	<div class="content">

   <h1>${login.id} 님의 페이지입니다.</h1> <br>

   <input class="but" id="update" value="회원정보 변경" type="button" style="margin-left: 32px;">
   <input class="but" id="mylist" value="마이게시글" type="button">
   <input class="but" id="main" value="메인으로 가기" type="button" onclick="location.href='main.do'">
   <input class="but" id="del" type="button"
      onclick="location.href='delete.do?id=${login.id}'" id="del"   value="회원 탈퇴"> <br>

   <iframe></iframe>
   
   </div>
  	
  		<div id="footer">Copyright ⓒ Ezen TeamProject</div>
	</div>
	
   <script type="text/javascript">
      
      $(document).ready(function(){
    	 $('iframe').hide();
         
         $("#del").click(function(){
            
            var choice;
            var url = "delete.do?id=${login.id}";
            choice = confirm("정말로 탈퇴하시겠습니까?");
            
            if (choice) {
               location.replace(url);
            } else {
               location.replace("read.do?id=${login.id}");
            }
            
         });
         $("#update").click(function(event) {
             event.preventDefault();
             $('iframe').show();
             $("iframe").attr("src", "updateui.do?id=${login.id}").attr("width","820px").attr("height","800")
          });
          
          $("#mylist").click(function(event) {
             event.preventDefault();
             $('iframe').show();
             $("iframe").attr("src", "bd_mylist.do?id=${login.id}").attr("width","820px").attr("height","500")
          });
         
         
      });

   </script>



</body>
</html>