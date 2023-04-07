<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>도서 등록</h2>
	<hr>
	<form action="insertBookOK.do">
		도서번호: <input type="number" name="bookid" value="${bookid }"><br>
		도서명: <input name="bookname" required="required"><br>
		출판사: <input name="publisher" required="required"><br>
		가격: <input type="number" name="price" required="required"><br>
		<input type="submit" value="확인">
		<input type="reset" value="리셋">
	</form>
</body>
</html>