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
	<h2>전체 사원 목록</h2>
	<hr>
	<table>
		<tr>
			<td>사원번호</td>
			<td>사원이름</td>
			<td>부서번호</td>
			<td>급여</td>
		</tr>
		<c:forEach var="e" items="${list }">
			<tr>
				<td>${e.eno }</td>
				<td><a href="detailEmp.do?eno=${e.eno }">${e.ename }</a></td>
				<td>${e.dno }</td>
				<td>${e.salary }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>