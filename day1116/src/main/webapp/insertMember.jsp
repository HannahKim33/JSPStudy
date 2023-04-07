<%@page import="com.sist.vo.MemberVO"%>
<%@page import="com.sist.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVO m=new MemberVO();
	m.setName(request.getParameter("name"));
	m.setAge(Integer.parseInt(request.getParameter("age")));
	m.setAddr(request.getParameter("addr"));
	MemberDAO dao=MemberDAO.getInstance();
	int re=dao.insertMember(m);
	response.setContentType("text/plain");
	out.print(re);
	
%>