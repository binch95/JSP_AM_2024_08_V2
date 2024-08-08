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
	<script type="text/javascript">
	
		function JoinForm__submit(form){			
			if (form.id.value.trim().length == 0){
				alert('아이디 써');
				return;
			}
			
			if (form.pw.value.trim().length == 0){
				alert('비번 써');
				return;
			}
			
			if (form.pwcf.value.trim().length == 0){
				alert('비번 확인 써');
				return;
			}
			
			if(form.pw.value != form.pwcf.value){
				alert('비번 일치 X');
				form.form.pw.value.trim().focus();
				return;
			}
			
			if (form.name.value.trim().length == 0){
				alert('이름 써');
				return;
			}			
			
			form.submit();

		}
	</script>


	<form method="POST" action="doSign" onsubmit="JoinForm__submit(this); return false;">
		<div>
			ID : <input autocomplete="off" type="text" placeholder="아이디 입력해" name="id" />
		</div>
		<div>
			PW : <input autocomplete="off" type="text" placeholder="비밀번호 입력해" name="pw" />
		</div>
		<div>
			PWcf : <input autocomplete="off" type="text" placeholder="비밀번호 입력해" name="pwcf" />
		</div>
			<div>
			name : <input autocomplete="off" type="text" placeholder="이름 입력해" name="name" />
		</div>
		<button type="submit">가입</button>
	</form>

</body>
</html>