<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${re == 1 }">
		게시물 등록을 성공하였습니다.
	</c:if>
	<c:if test="${re != 1 }">
		게시물 등록에 실패하였습니다.
	</c:if>
	<hr>
	<a href="listBoard.do">게시물 목록</a>
</body>
</html>