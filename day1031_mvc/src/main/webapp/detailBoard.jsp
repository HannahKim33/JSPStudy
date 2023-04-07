<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 조회</h2>
	<hr>
		글번호 : ${b.no } <br>
		글제목 : ${b.title } <br>
		작성자 : ${b.writer }  <br>
		내용 : <br>
		<textarea rows="10" cols="60" readonly="readonly">${b.content }</textarea><br>
		작성일 : ${b.regdate }<br>
		조회수 : ${b.hit }<br>
		<img src="upload/${b.fname }" width="100" height="100"> <br>
		첨부파일명 : <a href="data/">${b.fname }</a><br>
		<a href="updateBoard.do?no=${b.no }">수정</a>&nbsp;&nbsp;
		<a href="deleteBoard.do?no=${b.no }">삭제</a>&nbsp;&nbsp;
		<a href="insertBoard.do?no=${b.no }">답글 작성</a>
</body>
</html>