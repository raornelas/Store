<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loja - Registrar</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<h1>Registrar</h1>
	<form action="RegistraController" method="post">
		<table>

			<tr>
				<td>Nome</td>
				<td><input type="text" name="nome"></td>
			</tr>
			<tr>
				<td>Usuário</td>
				<td><input type="text" name="usuario"></td>
			</tr>
			<tr>
				<td>Senha</td>
				<td><input type="password" name="senha"></td>
			</tr>
			<tr>
				<td>Endereco</td>
				<td><input type="text" name="endereco"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Enviar"></td>
				<td></td>
			</tr>


			</form>
		</table>
</body>
</html>