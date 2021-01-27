<%@page import="kr.co.ezen.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

   <link rel="stylesheet" type="text/css" href="main.css" />
</head>
<body>
<!-- Begin Wrapper -->
<div id="wrapper">
  <!-- Begin Header -->
  <div id="header"><h1><a href="index.jsp">Main Page</a></h1></div>
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
  <div id="content"> 
  <br>
     <h1>인기 게시글</h1>
     <br><br><br>
     <table id="keywords">
        <thead>
           <tr>
              <th>글 번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>조회수</th>
              <th>작성일</th>         
           </tr>
        </thead>
        <tbody>
          
           <c:forEach items="${list}" var="dto" end="5">
            <tr>
              <td>${dto.num}</td>
              <td>
              <c:forEach begin="1" end="${dto.repIndent}">
                              &nbsp;Re:
                </c:forEach> <a href="bd_read.do?num=${dto.num}">${dto.title}</a>
            </td>
              <td>${dto.id}</td>
              <td>${dto.readcnt}</td>
              <td>${dto.writeday}</td>
           </tr>
           </c:forEach>
          
        </tbody>
        
     </table>
     
  </div>

  <div id="footer"> Copyright ⓒ Ezen TeamProject </div>


</body>
</html>