<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사원 상세</h2>
	<hr>
	사원번호 : ${e.eno }<br>
	사원이름 : ${e.ename }<br>
	부서번호 : ${e.dno }<br>	
	급여 : ${e.salary }<br>	
	성과급 : ${e.comm }<br>	
	입사일 : ${e.hiredate }<br>	
	연락처 : ${e.phone }<br>	
	주소 : ${e.addr }<br>	
	매니저 : ${e.mgr }<br>	
	직급 : ${e.job }<br>
	이메일 : ${e.email }<br>
	주민번호 : ${e.jumin }<br>
</body>
</html>