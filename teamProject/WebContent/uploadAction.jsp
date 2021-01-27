<%@ page import="kr.co.file.FileDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
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
</head>
<body>



	<%
		String directory = application.getRealPath("/upload/");
		int maxSize = 1024*1024*100;
		String encoding = "UTF-8";
		MultipartRequest mpr = new MultipartRequest(request, directory, maxSize, encoding, 
				new DefaultFileRenamePolicy());
		
		String fileName = mpr.getOriginalFileName("file");
		String fileRealName = mpr.getFilesystemName("file");
		
		new FileDAO().upload(fileName, fileRealName);

	%>


<script type="text/javascript">
	
	
location.href="fileDownload.jsp";


</script>
	

</body>
</html>