<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <link rel="stylesheet" type="text/css" href="bd_list.css" />
</head>
<body>

<div id="wrapper">
 <div id="header"><h1><a href="index.jsp">Board List</a></h1></div>

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

  <div id="content"> 

	<table id="keywords">
		<thead>
			<tr>
				<th>글 번호</th>
				<th width="35%">제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td style="text-align: center;">
					<a style="text-decoration: none; color: #757E7F;"
                  		href="bd_read.do?num=${dto.num}">
						<c:forEach begin="1" end="${dto.repIndent}">
                   			  &nbsp;Re:
                  		</c:forEach> 
						${dto.title}</a>
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
			<a href="bd_list.do?curPage=${to.beginLineNum-to.perLine}">&laquo;</a>
		</c:if>
		<c:if test="${to.curPage > 1 }">
			<a href="bd_list.do?curPage=${to.curPage - 1}">&lt;</a>
		</c:if>
		<c:forEach var="i" end="${to.stopLineNum}" begin="${to.beginLineNum}">
			<a class="${to.curPage == i?'itsme':'' }" href="bd_list.do?curPage=${i}"> ${i} </a>
		</c:forEach>

		<c:if test="${to.curPage < to.totalPage}">
			<a href="bd_list.do?curPage=${to.curPage + 1 }">&gt;</a>
		</c:if>

		<c:if test="${to.totalPage > to.stopLineNum}">
			<a href="bd_list.do?curPage=${to.beginLineNum + to.perLine}">&raquo;</a>
		</c:if>
	</div>
	

	<form action="bd_search.do" class="search">
		<select name="searchOption">
			<option value="id">작성자</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input name="searchKeyword" id="key"> 
		<input type="submit" value="검색" id="sub">
	</form>

	
	</div>
	
	<div id="footer"> Copyright ⓒ Ezen TeamProject </div>
	
</div>
</body>
</html>