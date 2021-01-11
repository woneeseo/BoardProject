<%@page import="kr.co.domain.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style type="text/css">

	.itsme{
		font-weight: bolder;
		color: red;
	}
	
	a{
		text-decoration: none;
	}
	
</style>

</head>
<body>
<a href="insertui.do">글쓰기</a>

<h1>게시글 목록</h1>

	<table>
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
		
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td>
							<!-- indent를 이용해서 답글들에 공백주기 : forEach문 이용하기 -->
							<!-- 답글의 경우, 원글 +1인 indent값을 가진다 -->
							<!-- forEach문을 이용해, indent가 가진 값 만큼 공백을 반복해서 출력해준다. -->
							
							<c:forEach begin="1" end="${dto.repIndent}">
								&nbsp;
							</c:forEach>
							
							<a href="read.do?num=${dto.num}">
							${dto.title}
						</a>
					</td>
					<td>${dto.author}</td>
					<td>${dto.readcnt}</td>
					<td>${dto.writeday}</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	
	<div>
		
		<jsp:include page="page.jsp"/>
		<!-- paging작업만을 하는 별도의 jsp파일 : include action태그를 이용해서 기능생성 -->
	
	</div>
	
	<!-- form태그 안에 select태그 : 검색기능 -->
	<!-- option태그에 어떤 옵션으로 검색할것인지 지정해준다 (value속성을 이용) -->
	<form action="search.do">
	
		<select name="searchoption">
			<option value="author">작성자</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
		</select>
		<input name="searchkeyword">
		<input type="submit" value="검색">

	</form>
</body>
</html>