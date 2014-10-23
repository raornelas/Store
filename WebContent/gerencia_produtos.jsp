<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.model.Compra"%>

<%@page import="br.com.loja.dao.UsuarioDAO"%>
<%@page import="br.com.loja.dao.ProdutoDAO"%>
<%@page import="br.com.loja.dao.CompraDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="java.text.DecimalFormat"%>

<%@page import="java.rmi.registry.LocateRegistry"%>
<%@page import="java.rmi.registry.Registry"%>
<%@page import="br.com.loja.dao.*"%>

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
	<h2>Estoque</h2>
	
	<a href="adicionaproduto.jsp">Adicionar novo produto</a>
	<br><br>	
	

	
	<table>
	<thead>
		<tr class="title">
		<th> Produto</th>
		<th> Preço</th>
		<th> Estoque</th>
		<th> Alterar</th>
		<th> Excluir </th>
	
		
	</tr>
	</thead>
		<%
			// Busca por todos os produtos e adiciona em lista
			List<Produto> lista = new ArrayList<Produto>();
			try {
				Registry registry = LocateRegistry.getRegistry(null);
				ProdutoDAO produtoDAO = (ProdutoDAO) registry
						.lookup("ProdutoDAO");
				lista = (List<Produto>) produtoDAO.buscarTodos();
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}

			// Percorre lisa
			for (Produto p : lista) {

				// Formatação de $
				DecimalFormat df = new DecimalFormat("0.##");

				// Formulário
		%>

	<tr>
		<td><%= p.getNome()%> </td>
		<td><%= df.format(p.getPreco_unitario()) %> </td>
		<td><%= p.getQtde_estoque() %> </td>
		
		<td><a href="altera_produto.jsp?id=<%=p.getId()%>"  class="label">Alterar</a></td>
		<td><a href="ProdutoController?acao=excluir&produto=<%=p.getId()%>"  class="label">Exluir</a></td>
	</tr>
		<%
			}
		%>
		</table>
</body>
</html>