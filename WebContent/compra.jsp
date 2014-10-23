<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>




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
	<h2>Pedido</h2>
	
	<%
	// Recupera produto na sessao
	String produto = (String) sessao.getAttribute("produto"); 
	
	// Se nao ha produto em sessao, recupera pelo método get 
	if (produto == null) produto = request.getParameter("produto");
	
	// Salva produto na sessao
	sessao.setAttribute("produto",produto);
	
	// Imprime nome do produto
	%><h4><br><%
	out.print(produto);	
	 %></h4><br><%
	String pagamento="";

	// Recupera método de pagamento da sessão
	if(sessao.getAttribute("pagamento")!=null)
		pagamento = (String) session.getAttribute(pagamento);
	
	
	// Inicia variáveis
	String quantidade="";
	String endereco="";
	String cartao="";
	
	// Preenche o formulário se dados estiverem na sessão (login após compra)
	if(sessao.getAttribute("quantidade")!=null)
		quantidade = (String) sessao.getAttribute("quantidade");
	
	if(sessao.getAttribute("cartao")!=null)
		cartao = (String) sessao.getAttribute("cartao");
	
	if(sessao.getAttribute("endereco")!=null)
		endereco = (String) sessao.getAttribute("endereco");
	
	
	%>
	
	<form action="CompraController" method="post">
	Quantidade
	<input type="text" name="quantidade" value="<%= quantidade%>">
	Pagamento<br>
	<input type="radio" name="pagamento" value="credito">
	Crédito 
	
	<input type="radio" name="pagamento" value="debito">
	Débito
	<br>
	Cartão
	<input type="text" name="cartao" value="<%= cartao%>">
	Endereço
	<input type="text" name="endereco" value="<%= endereco%>">
	<input type="submit" value="Comprar" class="button">
	</form>
</body>
</html>