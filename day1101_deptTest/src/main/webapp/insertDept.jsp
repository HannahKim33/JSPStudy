<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>부서 등록</h2>
	<hr>
	<form action="insertDeptOK.do" method="post">
		부서번호: <input type="number" name="dno"><br>
		부서이름: <input name="dname"><br>
		부서위치: <input name="dloc"><br>
		<input type="submit" value="확인">
		<input type="reset" value="리셋">
	</form>
</body>
</html>