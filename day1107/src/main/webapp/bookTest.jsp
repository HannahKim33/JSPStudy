<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		/*
		$.ajax({
			url:"GetTitle",
			success:function(data){
				$('#title').html(data);
			}
		}) 이것을 줄여서 아래와 같이 쓸 수 있음*/
		$('#title').load("GetTitle");
		
		$(document).on("click",".book", function(){
			var td=$(this).find("td");
			$('#bookid').val($(td[0]).html());
			$('#bookname').val($(td[1]).html());
			$('#publisher').val($(td[2]).html());
			$('#price').val($(td[3]).html());
		})
		
		/* 기존에 없던 요소는 이렇게 하면 이벤트 등록 안 됨
		$(".book").click(function(){
			alert("OK");
		})*/
		var listBook=function(){
			$("#list").empty();
			$.getJSON("ListBook",function(arr){
				$.each(arr, function(){
					var tr=$("<tr></tr>").addClass("book");
					var td1=$("<td></td>").html(this.bookid);
					var td2=$("<td></td>").html(this.bookname);
					var td3=$("<td></td>").html(this.publisher);
					var td4=$("<td></td>").html(this.price);
					
					$(tr).append(td1, td2, td3, td4);
					$('#list').append(tr);
				})
			});
		}
		
		listBook();
		
		$('#btnAdd').click(function(){
			$.ajax({
				url:"InsertBook",
				data:{
					bookid:$("#bookid").val(),
					bookname:$("#bookname").val(),
					publisher:$("#publisher").val(),
					price:$("#price").val()
					},
				success:function(re){
					alert(re);
					listBook();
				}
			})
		})
		
		$('#btnUpdate').click(function(){
			var bookid=$('#bookid').val();
			var bookname=$('#bookname').val();
			var publisher=$('#publisher').val();
			var price=$('#price').val();
			var data={bookid:bookid,
					bookname:bookname,
					publisher:publisher,
					price:price};
			$.post("UpdateBook",data,function(re){
				alert(re);
				listBook();
			})
		})
		
		$("#btnDelete").click(function(){
			if(confirm("정말로 삭제하시겠습니까?")){
				var data={bookid:$('#bookid').val()};
				$.get("DeleteBook",data,function(re){
					listBook();
				})
			}
		})
	})
</script>
</head>
<body>
	<h2 id="title"></h2>

	도서번호 : <input type="number" id="bookid"><br>
	도서이름 : <input type="text" id="bookname"><br>
	출판사 : <input type="text" id="publisher"><br>
	가격 : <input type="number" id="price"><br>
	<button id="btnAdd">등록</button>
	<button id="btnUpdate">수정</button>
	<button id="btnDelete">삭제</button>
	<br>
	<br>
	<hr>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>도서번호</th>		
				<th>도서명</th>		
				<th>출판사</th>		
				<th>가격</th>
			</tr>
		</thead>
		<tbody id="list"></tbody>		
	</table>
</body>
</html>