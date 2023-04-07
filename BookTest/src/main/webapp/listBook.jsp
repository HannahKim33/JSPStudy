<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="insertBook.do">도서 등록</a>
	<h2>도서 목록</h2>
	<hr>
	<table>
		<tr>
			<td>도서번호</td>
			<td>도서명</td>
		</tr>
		<c:forEach var="b" items="${list }">
			<tr>
				<td>${b.bookid }</td>
				<td><a href="detailBook.do?bookid=${b.bookid }">${b.bookname }</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>