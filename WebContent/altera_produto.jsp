<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.rmi.registry.LocateRegistry"%>
<%@page import="java.rmi.registry.Registry"%>
<%@page import="br.com.loja.dao.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar produto</title>
</head>
<body>
<%@include file="menu.jsp" %>
	<h1>Alterar</h1>
	<br>
	<br>
	<%
		// Recupera id enviado por gerencia_produtos.jsp
		String id = request.getParameter("id");
		
		// Transforma em int
		int id_produto = Integer.parseInt(id);
				
		// Busca objeto produto pelo id
		Produto produto = new Produto();
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			ProdutoDAO produtoDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
			produto = produtoDAO.buscarPorId(id_produto);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		

		// Adiciona na sessao o id do produto e ação para alterar
		// (A ser recuperado em ProdutoController)
		sessao.setAttribute("id_prod", id_produto);
		sessao.setAttribute("acao", "altera");

		// Formatação para $
		DecimalFormat df = new DecimalFormat("0.##");

		// Formulário
	%>
	<form action="ProdutoController" method="get">
	Nome<br><input type="text" name="nome" value="<%= produto.getNome() %>"><br>
	Quantidade<br><input type="text" name="quantidade" value="<%= produto.getQtde_estoque() %>"><br>
	Preço<br><input type="text" name="preco" value="<%= df.format(produto.getPreco_unitario()) %>"><br>
	Descrição<br><textarea rows="5" cols="33" name="descricao"><%= produto.getDescricao() %></textarea><br>

	<input type="submit" value="Enviar" />
	</form>
	<br>
</body>
</html>