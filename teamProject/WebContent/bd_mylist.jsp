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
<link href="bd_mylist.css" rel="stylesheet" type="text/css">
</head>
<body>


<div id="wrapper">

  <div id="content"> 

<table id="keywords">
   <thead>
      <tr>
         <th>글번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>조회수</th>
         <th>작성일</th>
         
      </tr>
   </thead>
   
   <tbody>
      <c:forEach items="${myList}" var="dto">
         <tr>
            <td>${dto.num}</td>
            <td>
               	<a href="bd_read.do?num=${dto.num}">
                  <c:forEach begin="1" end="${dto.repIndent}">
                     &nbsp;&nbsp;
                  </c:forEach>
                 ${dto.title}
               </a>
            </td>
            <td>${dto.id}</td>
            <td>${dto.readcnt}</td>
            <td>${dto.writeday}</td>
            
         </tr>
      
      </c:forEach>
   
   </tbody>

</table>

	<div class="page">
		<c:if test="${to.beginLineNum - to.perLine > 0 }">
			<a href="bd_mylist.do?curPage=${to.beginLineNum-to.perLine}">&laquo;</a>
		</c:if>
		<c:if test="${to.curPage > 1 }">
			<a href="bd_mylist.do?curPage=${to.curPage - 1}">&lt;</a>
		</c:if>
		<c:forEach var="i" end="${to.stopLineNum}" begin="${to.beginLineNum}">
			<a class="${to.curPage == i?'itsme':'' }" href="bd_mylist.do?curPage=${i}"> ${i} </a>
		</c:forEach>

		<c:if test="${to.curPage < to.totalPage}">
			<a href="bd_mylist.do?curPage=${to.curPage + 1 }">&gt;</a>
		</c:if>

		<c:if test="${to.totalPage > to.stopLineNum}">
			<a href="bd_mylist.do?curPage=${to.beginLineNum + to.perLine}">&raquo;</a>
		</c:if>
	</div>
</div>
</div>
</body>
</html>