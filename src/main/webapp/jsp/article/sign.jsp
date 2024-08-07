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

	<h2>회원가입</h2>

	<form method="POST" action="doSign">
		<div>
			ID : <input type="text" placeholder="아이디 입력해" name="id" />
		</div>
		<div>
			PW : <input type="text" placeholder="비밀번호 입력해" name="pw" />
		</div>
		<div>
			PWcf : <input type="text" placeholder="비밀번호 입력해" name="pwcf" />
		</div>
			<div>
			name : <input type="text" placeholder="이름 입력해" name="name" />
		</div>
		<button type="submit">가입</button>
	</form>

</body>
</html>