<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="fileDownload.css" />
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
  			<input name="logid" value="${login.id}" hidden="">
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

	<h1>파일을 클릭하시면 다운로드 하실 수 있습니다</h1>
	<br>
	<table id="keywords">
	<thead>
		<tr>
			<th>파일명</th>
		</tr>
	</thead>
	<tbody>
	<tr>
	<td>
	
	<%
		String directoty = application.getRealPath("/upload/");
		String files[] = new File(directoty).list();

		for(String file : files){
			out.write("<a href=\""+request.getContextPath()+"/downloadAction?file="+URLEncoder.encode(file, "UTF-8")
			+ "\">" + file + "</a><br><br>");
		}
	
	%>
	</td>
	</tr>
	</tbody>
	</table>
	
	<button id="upload">업로드 하기</button>
	
	</div>
	
	
  <div id="footer"> Copyright ⓒ Ezen TeamProject </div>
	
</div>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#upload").click(function(event){
			
			var id = $("input[name='logid']").val();
			
			if (id == null) {
				
				alert("업로드를 하기 위해서는 로그인이 필요합니다.");
				location.replace("loginui.do");
				
			} else {
				
				location.replace("upload.jsp");
			}
			
		});
		
	});

</script>
</body>
</html>