<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<a href="../home/main">메인 페이지로 </a>

	<h2>로그인</h2>

	<form method="POST" action="doLogin">
		<div> 
			ID : <input type="text" placeholder="아이디 입력해" name="id" />
		</div>
		<div>
			PW : <input type="text" placeholder="비밀번호 입력해" name="pw" />
		</div>
		<button type="submit">로그인</button>
	</form>



	<div>
		<a style="color: green" href="sign">회원가입하기</a>
	</div>

</body>
</html>