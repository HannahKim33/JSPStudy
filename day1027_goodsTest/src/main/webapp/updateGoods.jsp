<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="com.sist.dao.GoodsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int no=Integer.parseInt(request.getParameter("no"));
	GoodsDAO dao=new GoodsDAO();
	GoodsVO g=dao.findByNo(no);
%>
	<h2>상품 수정</h2>
	<form action="updateGoodsOK.jsp" method="post" enctype="multipart/form-data">
		상품번호 : <%=no %>
		<input type="hidden" name="no" value="<%=no%>"><br>
		상품이름 : <input name="name" value="<%=g.getName()%>"><br>
		수량 : <input type="number" name="qty" value="<%=g.getQty()%>"><br>
		가격 : <input type="number" name="price" value="<%=g.getPrice()%>"><br>
		<input type="hidden" name="oldFname" value="<%=g.getFname()%>">
		사진 : <img src="data/<%=g.getFname()%>"><br> 
		<input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
		<input type="reset" value="다시 쓰기">
	</form>
</body>
</html>