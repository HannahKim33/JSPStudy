<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	function checkDelete(dno){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="deleteDept.do?dno="+dno;
		}
	}
</script>
</head>
<body>
	<h2>부서 상세</h2>
	<hr>
	부서번호 : ${d.dno }<br>
	부서이름 : ${d.dname }<br>
	부서위치 : ${d.dloc }<br>
	<a href="updateDept.do?dno=${d.dno }">수정</a>
	<a href="#" onclick="checkDelete(${d.dno})">삭제</a>
</body>
</html>