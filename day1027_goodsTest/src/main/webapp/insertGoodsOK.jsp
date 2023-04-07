<%@page import="com.sist.dao.GoodsDAO"%>
<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	request.setCharacterEncoding("utf-8");
	String path=request.getRealPath("data");
	MultipartRequest multi=new MultipartRequest(request, path, 1024*1024*5, "utf-8",
			new DefaultFileRenamePolicy());
	int no=Integer.parseInt(multi.getParameter("no"));
	String name=multi.getParameter("name");
	int qty=Integer.parseInt(multi.getParameter("qty"));
	int price=Integer.parseInt(multi.getParameter("price"));
	
	String fname="";
	if(multi.getFile("uploadFile")!=null){
		fname=multi.getFile("uploadFile").getName();
	}

	GoodsVO g=new GoodsVO(no, name, qty, price, fname);
	GoodsDAO dao=new GoodsDAO();
	
	int re=dao.insertGoods(g);
	if (re>0){
		%>
			성공<br>
			<a href="listGoods.jsp">목록으로</a>
		<%
	}else{
		out.print("실패");
		if(!fname.equals("")){
			File file2=new File(path+"/"+fname);
			file2.delete();
		}
		%>
		<a href="insertGoods.jsp">뒤로가기</a>
		<%
	}
%>
</body>
</html>