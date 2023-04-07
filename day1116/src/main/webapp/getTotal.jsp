<%@page import="com.sist.vo.MemberVO"%>
<%@page import="com.sist.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	MemberVO m=new MemberVO();
	MemberDAO dao=MemberDAO.getInstance();
	int re=dao.getTotalRecord();
	response.setContentType("text/plain");
	out.print(re);
	
%>