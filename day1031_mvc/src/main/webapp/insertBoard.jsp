<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${title }</h2>
	<form action="insertBoardOK.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${no }">
		작성자: <input name="writer" required="required"><br>
		비밀번호: <input type="password" name="pwd" required="required"><br>
		제목: <input name="title" required="required"><br>
		내용: <textarea rows="10" cols="60" name="content" required="required"></textarea><br>
		첨부파일: <input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
		<input type="reset" value="다시 입력">
	</form>
</body>
</html>