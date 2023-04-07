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
	<c:if test="${re>0 }">
		수정 성공
	</c:if>
	<c:if test="${re<=0 }">
		수정 실패
	</c:if>
	<br><a href="listDept.do">부서 목록</a>
</body>
</html>