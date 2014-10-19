<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loja - Gerenciamento</title>
</head>
<body>
<%@include file="menu.jsp" %>
	<h1>Produtos</h1>
	<br>
	<h3>Adicionar novo produto</h3>
	<br>

	<form action="ProdutoController" method="post">
	Nome<br><input type="text" name="nome"><br>
	Quantidade<br><input type="text" name="quantidade"><br>
	Preço<br><input type="text" name="preco"><br>
	Descrição<br><textarea rows="5" cols="33" name="descricao"></textarea><br>

	<input type="submit" value="Enviar" />
	</form>
	<br>
	
</body>
</html>