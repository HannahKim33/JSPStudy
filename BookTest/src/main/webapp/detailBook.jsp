<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	function checkDelete(bookid){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="deleteBook.do?bookid="+bookid;
		}
	}
</script>
</head>
<body>
	<h2>도서 상세</h2>
	<hr>
	도서 번호 : ${b.bookid }<br>
	도서 이름 : ${b.bookname }<br>
	출판사 : ${b.publisher }<br>
	가격 : ${b.price }<br>
	<a href="updateBook.do?bookid=${b.bookid }">수정</a>
	<a href="#" onclick="checkDelete(${b.bookid })">삭제</a>
	<a href="listBook.do">목록</a>	
</body>
</html>