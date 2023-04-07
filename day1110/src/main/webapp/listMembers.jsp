<%@page import="com.google.gson.Gson"%>
<%@page import="com.sist.vo.MembersVO"%>
<%@page import="com.sist.dao.MembersDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MembersDAO dao=MembersDAO.getInstance();
ArrayList<MembersVO> list=dao.findAll();
Gson gson=new Gson();
String str=gson.toJson(list);
out.print(str);

%>