<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 등록</h2>
	<form action="insertGoodsOK.jsp" method="post" enctype="multipart/form-data">
		상품번호 : <input type="number" name="no" required="required"><br>
		상품이름 : <input name="name" required="required"><br>
		수량 : <input type="number" name="qty" required="required"><br>
		가격 : <input type="number" name="price" required="required"><br>
		사진 : <input type="file" name="uploadFile" required="required"><br>
		<input type="submit" value="등록">
		<input type="reset" value="다시 쓰기">
	</form>
</body>
</html>