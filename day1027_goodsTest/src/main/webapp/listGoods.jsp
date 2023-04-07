<%@page import="java.util.HashMap"%>
<%@page import="com.sist.dao.GoodsDAO"%>
<%@page import="com.sist.vo.GoodsVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#op{
		/* visibility: hidden;*/
		display: none;
	}


</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#searchColumn").change(function(){
			var selectedColumn=$(this).val();
			if(selectedColumn=="price" || selectedColumn=="qty"){
				$('#op').css("display","inline");
			}
			else{
				$('#op').css("display","none");
			}
		})
	})
</script>
</head>
<body>
	<h2>상품 목록</h2>
	<hr>
	<table border='1' cellspacing='0' cellpadding='5'>
		<thead>
			<tr>
				<th>상품번호 <a href="listGoods.jsp?sortColumn=no">▼</a></th>
				<th>상품이름 <a href="listGoods.jsp?sortColumn=name">▼</a></th>
				<th>상품가격 <a href="listGoods.jsp?sortColumn=price">▼</a></th>
				<th>상품수량 <a href="listGoods.jsp?sortColumn=qty">▼</a></th>
			</tr>
		</thead>
		<tbody>
			<%
				String sortColumn="";
				String searchWord="";
				String searchColumn="";
				String op="";
				
				HashMap<String, String> map=new HashMap<String, String>();
				
				if(session.getAttribute("map") != null){
					map=(HashMap<String, String>)session.getAttribute("map");
					op=map.get("op");
					searchWord=map.get("searchWord");
					searchColumn=map.get("searchColumn");
				}
				
				if(request.getParameter("searchWord")!=null){
					op=request.getParameter("op");
					searchWord=request.getParameter("searchWord");
					searchColumn=request.getParameter("searchColumn");
				}
				
				sortColumn=request.getParameter("sortColumn");
				
				System.out.println("searchWord attr: "+session.getAttribute("searchWord"));

				System.out.println("searchWord:"+searchWord);
				System.out.println("searchColumn:"+searchColumn);
				System.out.println("정렬칼럼:"+sortColumn);
				System.out.println("연산자:"+op);
				
				GoodsDAO dao=new GoodsDAO();
				
				
				map.put("sortColumn",sortColumn);
				map.put("searchWord",searchWord);
				map.put("searchColumn",searchColumn);
				map.put("op",op);
				
				ArrayList<GoodsVO> list=dao.findAll(map);
				session.setAttribute("map", map);
				
				for(GoodsVO g:list){
					%>
					<tr>
						<td><%=g.getNo() %></td>
						<td><a href="detailGoods.jsp?no=<%=g.getNo()%>"><%=g.getName() %></a></td>
						<td><%=g.getPrice() %></td>
						<td><%=g.getQty() %></td>
					</tr>
					<%
				}
			%>
		</tbody>
	</table>
	<a href="insertGoods.jsp">상품 등록</a>
	<form action="listGoods.jsp">
		<select name="searchColumn" id="searchColumn">
			<option value="no">상품번호</option>
			<option value="name">상품이름</option>
			<option value="price">상품가격</option>
			<option value="qty">상품수량</option>
		</select>
		<select name="op" id="op">
			<option value=">=">&ge;</option>
			<option value="<=">&le;</option>
			<option value="="> = </option>
			<option value=">">&gt;</option>
			<option value="<">&lt;</option>
		</select>
		<input type="search" name="searchWord" id="searchWord">
		<input type="submit" value="검색">
	</form>
</body>
</html>