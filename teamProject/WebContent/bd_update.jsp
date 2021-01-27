<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <!DOCTYPE html>
 <html>
 <head>
 <meta charset="UTF-8">
 <title>게시글 수정</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <link rel="stylesheet" type="text/css" href="bd_update.css" />
 </head>
 <body>
 
 <div id="wrapper">

    <div id="header"><h1><a>게시글 수정</a></h1></div>

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


  <div class="content"> 

 <form action="bd_update.do" method="post">
 
 	<label>
 		 <input id="title" name="title" value="${dto.title}" required>
 	</label>
 	
 <div id="con">
 
 	<label class="lab" style="margin-top: 10px;">

   		<span style="margin-left: 50px;">글 번호</span> <input id="num" name="num" value="${dto.num}" style="width: 70px;" readonly>
    	<span>글쓴이: </span><input id="id" name="id" value="${dto.id}" readonly>
     	<span>조회수: </span><input name="readcnt" id="readcnt" value="${dto.readcnt}" style="width: 70px;" readonly>
        <span>작성일: </span><input name="writeday" id="writeday" value="${dto.writeday}" readonly>
    </label>
 </div>
 	<label>
 		 <textarea id="feedback" rows="5" name="content" required>${dto.content}</textarea>
 	</label>
 	
 	
   <input class="but" class= "edit" type="submit" value="수정 완료" style="margin-left: 120px;">
   <input class="but" id="back" type="button" value="돌아가기">
	<input id="del" class="but" type="button" value="삭제">       
	
 <script type="text/javascript">
       
    $(document).ready(function() {
    	
       
       $("#back").click(function(event){
          
          event.preventDefault();
          history.go(-1);
          
       });
       
       $("#del").click(function(event) {

			var choice;
			choice = confirm("게시글을 삭제하시겠습니까?");

			if (choice) {
				location.replace("bd_delete.do?num=${dto.num}");
			} else {
				location.replace("bd_read.do?num=${dto.num}")
			}

			});
       
    });

    </script>
   
 </form>
 
 </div>

  <div id="footer"> Copyright ⓒ Ezen TeamProject </div>
  </div>
 </body>
 </html> 