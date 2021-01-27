<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 화면</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="bd_reply.css" />
</head>
<body>

<div id="wrapper">
   <div id="header">
      <h1><a>Reply content : ${param.num}</a></h1>
   </div>
   
   <div id="navigation">
  
    <c:choose>
  		<c:when test="${empty login}">
  			<a href="insertui.do">회원 가입</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="loginui.do">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
  			<a href="bd_list.do">글 목록</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
  			<a href="fileDownload.jsp">자료실</a>
  		</c:when>
  		<c:otherwise>
  			<a href="bd_insertui.do">글 쓰기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="read.do?id=${login.id}">마이페이지</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<a href="logout.do">로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
  			<a href="bd_list.do">글 목록</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
  			<a href="fileDownload.jsp">자료실</a>
  		</c:otherwise>
  	</c:choose>

  </div>

   <form action="bd_reply.do" method="post" class="content">
   
      <div style="padding-left: 35px; width: 800px;">
         <label> <input type="hidden" name="num" value="${param.num}"
            type="text">
         </label> 
         
         <label> <span>작성자</span> <input name="id" id="author"
            value="${login.id}" type="text" readonly>
         </label>
         
         <label> <span>제목</span><input name="title" id="title" placeholder="제목을 입력해주세요">
         </label> 
         
         <label> <span>내용</span> <textarea rows="5" name="content"
               placeholder="내용을 입력해주세요" id="feedback"></textarea>
         </label>
         
         <label>
         <input type="submit" value="답글등록" id="replybutton">
         
         </label>
         
         <label>
           <button id="button" >작성취소</button>
         </label>

      </div>
   </form>

   <div id="footer"> Copyright ⓒ Ezen TeamProject </div>

<script type="text/javascript">

	$(document).ready(function(){
		
		
		
		$("#button").click(function(event){
			
			event.preventDefault();
			history.go(-1);
			
		});
	});

</script>

</div>
</body>
</html>