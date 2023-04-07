<%@page import="com.google.gson.Gson"%>
<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="com.sist.dao.GoodsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
GoodsDAO dao=GoodsDAO.getInstance();
ArrayList<GoodsVO> list=dao.findAll();
Gson gson=new Gson();
String str=gson.toJson(list);
out.print(str);

%>