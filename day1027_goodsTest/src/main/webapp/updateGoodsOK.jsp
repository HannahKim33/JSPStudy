<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	MultipartRequest multi=new MultipartRequest(request, path, 1024*1024*5, "utf-8",
			new DefaultFileRenamePolicy());
	GoodsVO g=new GoodsVO();
	g.setNo(Integer.parseInt(multi.getParameter("no")));
	g.setName(multi.getParameter("name"));
	g.setQty(Integer.parseInt(multi.getParameter("qty")));
	g.setPrice(Integer.parseInt(multi.getParameter("price")));
	
	String oldFname=multi.getParameter("oldFname");
	String fname=oldFname;
	File file=multi.getFile("uploadFile");
	if(file!=null){
		fname=file.getName();
	}
	g.setFname(fname);
	
	GoodsDAO dao=new GoodsDAO();
	int re=dao.updateGoods(g);
	
	if(re>0){
		out.print("수정 성공<br>");
		out.print("<a href='listGoods.jsp'>목록으로</a>");
		if(file!=null){
			File file2=new File(path+"/"+oldFname);
			file2.delete();
		}
	}else{
		if(file!=null){
			file.delete();
		}
		out.print("수정 실패");
		out.print("<a href='updateGoods.jsp'>뒤로가기</a>");
	}
	
	
%>
</body>
</html>