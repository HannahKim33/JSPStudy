<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>도서 수정</h2>
	<hr>
	<form action="updateBookOK.do">
		도서번호: ${b.bookid } <input type="hidden" name="bookid" value="${b.bookid }" required="required"><br>
		도서명: <input name="bookname" value="${b.bookname }" required="required"><br>
		출판사: <input name="publisher" value="${b.publisher }" required="required"><br>
		가격: <input type="number" name="price" value="${b.price }" required="required"><br>
		<input type="submit" value="확인">
		<input type="reset" value="리셋">
	</form>
</body>
</html>