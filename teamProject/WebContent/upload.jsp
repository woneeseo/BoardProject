<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="upload.css" />
</head>
<body>

<!-- Begin Wrapper -->
<div id="wrapper">
  <!-- Begin Header -->
  <div id="header"><h1><a href="index.jsp">File Archive</a></h1></div>
  <!-- End Header -->
  <!-- Begin Naviagtion -->
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
  <!-- End Naviagtion -->
  <!-- Begin Content -->
  <div id="content" style="height: auto;"> 

<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">

	<h1>업로드할 파일을 입력해주세요.</h1>
	<br>
	<table id="keywords">
	<tr>
	<td class="td">파일</td>
	<td>
	<input type="file" name="file" id="file">
	</td>
	</tr>
	<tr>
	<td class="td">미리보기</td>
	<td>
	<img id="image_section" src="#" alt="your image" width="500" height="300"/>
	</td>
	</tr>
	<tr>
	<td colspan="2">
	<input type="submit" id="up" value="업로드">
	</td>
	</tr>
	</table>
</form>

<button onclick="location.href='fileDownload.jsp'">파일 다운로드 바로가기</button>

</div>


  <div id="footer"> Copyright ⓒ Ezen TeamProject </div>

</div>
<script>

         function readURL(input) {
            if (input.files && input.files[0]) {
               var reader = new FileReader();

               reader.onload = function(e) {
                  $('#image_section').attr('src', e.target.result);
               }

               reader.readAsDataURL(input.files[0]);
            }
         }

         $("#file").change(function() {
            readURL(this);
         });
      </script>
</body>
</html>