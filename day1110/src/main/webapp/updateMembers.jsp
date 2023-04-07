<%@page import="com.sist.vo.MembersVO"%>
<%@page import="com.sist.dao.MembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MembersVO m=new MembersVO();
m.setNo(Integer.parseInt(request.getParameter("no")));
m.setName(request.getParameter("name"));
m.setGender(request.getParameter("gender"));
m.setAddr(request.getParameter("addr"));
m.setBloodType(request.getParameter("bloodType"));
MembersDAO dao=MembersDAO.getInstance();
int re=dao.updateMembers(m);
out.print(re);
%>