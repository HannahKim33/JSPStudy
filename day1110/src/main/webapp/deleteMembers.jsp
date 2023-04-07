<%@page import="com.sist.vo.MembersVO"%>
<%@page import="com.sist.dao.MembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int no=Integer.parseInt(request.getParameter("no"));
MembersDAO dao=MembersDAO.getInstance();
int re=dao.deleteMembers(no);
out.print(re);
%>