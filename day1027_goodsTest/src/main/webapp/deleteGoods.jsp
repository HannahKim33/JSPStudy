<%@page import="java.io.File"%>
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
	String path=request.getRealPath("data");
	int no=Integer.parseInt(request.getParameter("no"));
	GoodsDAO dao=new GoodsDAO();
	GoodsVO g=dao.findByNo(no);
	String fname=g.getFname();
	int re=dao.deleteGoods(no);
	if(re>0){
		out.print("삭제 성공<br>");
		new File( path+"/"+fname).delete();
		%><a href="listGoods.jsp">목록으로</a><%
	}else{
		out.print("삭제 실패<br>");
		%><a href="detailGoods.jsp">상세보기 화면으로</a><%
	}
%>
</body>
</html>