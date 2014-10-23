<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loja</title>
<link rel="stylesheet" href="foundation.min.css">
</head>
<body>
<%@include file="menu.jsp" %>
	<br>
	<h2>Home</h2>
	Compra realizada com sucesso.
	<br><br><br>
	<a href="index.jsp"><< Voltar</a>
</body>
</html>