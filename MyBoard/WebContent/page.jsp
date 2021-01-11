<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
    <c:if test="${to.beginLineNum-to.perLine > 0}">
    	<a href="list.do?curPage=${to.beginLineNum-to.perLine}">&laquo;</a>
    	<!-- < 를 클릭하면 현재페이지가 이동한다.list.do?curPage= -->
    	<!-- 어디로 이동하는가? beginLineNum에서 +/- perLine 만큼 -->
    	<!-- 단, beginLineNum이 1이면 <<표시가 나오지 않아야 한다 -->
    </c:if>
    
   	<c:if test="${to.curPage > 1}">
    	<a href="list.do?curPage=${to.curPage-1}">&lt;</a>
    	<!-- -1을 계속하다보면 curPage가 마이너스 값을 가져도 화면이 보여진다 -->
    	<!-- 따라서 1이하로는 화면이 넘어가지 않도록 조건을 부여해 처리한다 -->
    </c:if>
    
    <c:forEach var="i" begin="${to.beginLineNum}" end="${to.stopLineNum}" >
		<a class="${to.curPage == i ? 'itsme' : ''}" href="list.do?curPage=${i}">${i}</a>
		<!-- EL의 삼항연산자를 이용해서 현재페이지와 i값이 같으면 itsme라는 클래스를 부여해서 -->
		<!-- style태그에 부여해준 속성을 갖도록 함 / 다르면 클래스를 부여해주지 않는다. -->
	</c:forEach>
	
	<c:if test="${to.curPage < to.totalPage}">
		<a href="list.do?curPage=${to.curPage+1}">&gt;</a>
	</c:if>
	
	<c:if test="${to.stopLineNum < to.totalPage}">
		<a href="list.do?curPage=${to.beginLineNum + to.preLine}">&raquo;</a>
	</c:if>