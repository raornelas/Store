<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<h1>Login</h1>
	<br>
	<form action="LoginController" method="post">
		<table>
			<tr>
				<td>Usuário:</td>
				<td><input type="text" name="username" size="10"></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type="password" name="password" size="10"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Entrar" /></td>
			</tr>
		</table>
	</form>
</body>
</html>