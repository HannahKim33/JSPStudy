<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="com.sist.dao.GoodsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#deleteGoods').click(function(){
			if(confirm("정말로 삭제하시겠습니까?")){
				var no=$("#no").val();
				location.href="deleteGoods.jsp?no="+no;
			}
		});
	});
	
</script>
</head>
<body>
<%
int no=Integer.parseInt(request.getParameter("no"));
GoodsDAO dao=new GoodsDAO();
GoodsVO g=dao.findByNo(no);
%>
	<h2>상품 상세</h2>
	<input type="hidden" id="no" value="<%=no %>">
	<table>
		<tr>
			<td>상품번호</td>
			<td><%=g.getNo() %></td>
		</tr>
		<tr>
			<td>상품이름</td>
			<td><%=g.getName() %></td>
		</tr>
		<tr>
			<td>수량</td>
			<td><%=g.getQty() %></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><%=g.getPrice() %></td>
		</tr>
		<tr>
			<td>사진</td>
			<td><img src="data/<%=g.getFname()%>"></td>
		</tr>
	</table>
	<a href="updateGoods.jsp?no=<%=no %>">수정</a>
	<a href="#" id="deleteGoods">삭제</a>
</body>
</html>