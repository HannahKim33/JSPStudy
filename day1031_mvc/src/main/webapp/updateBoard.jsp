<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시물 수정</h2>
	<hr>
	<form action="updateBoardOK.do" method="post" enctype="multipart/form-data">
		글번호: ${b.no }<input type="hidden" name="no" value="${b.no }"><br>
		글제목: <input name="title" value="${b.title }"><br>
		작성자: ${b.writer }<input type="hidden" value="${b.writer }">  <br>
		암호 : <input type="password" name="pwd" required="required"><br>
		내용: <br>
		<textarea rows="10" cols="60" name="content">${b.content }</textarea><br>
		작성일: ${b.regdate }<br>
		조회수: ${b.hit }<br>
		
		첨부파일명: ${b.fname }<br>
		<input type="hidden" name="oldFname" value="${b.fname }"><br>
		첨부파일: <input type="file" name="uploadFile"><br>
		<input type="submit" value="확인">
		<input type="reset" value="리셋">
	</form>
</body>
</html>