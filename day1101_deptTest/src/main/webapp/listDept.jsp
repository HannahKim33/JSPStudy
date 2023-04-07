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
	<a href="insertDept.do">부서 등록</a>
	<h2>부서목록</h2>
	<hr>
	<ul>
		<c:forEach var="b" items="${list }">
			<li><a href="detailDept.do?dno=${b.dno}">${b.dname }</a></li>
		</c:forEach>
		
	</ul>
</body>
</html>