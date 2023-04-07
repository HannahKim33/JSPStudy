<%@page import="com.google.gson.Gson"%>
<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.dao.GoodsDAO"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	GoodsDAO dao=new GoodsDAO();
	ArrayList<GoodsVO> list=dao.findAll();
	String str="";
	Gson gson=new Gson();
	str=gson.toJson(list);
%>
<%=str%>