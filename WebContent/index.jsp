<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.dao.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>

<%@page import="java.rmi.registry.LocateRegistry"%>
<%@page import="java.rmi.registry.Registry"%>

<%@page import="java.net.MalformedURLException"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loja</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<h1>Loja</h1>
	<br>
	<br>
	<table border="1" cellpadding="5">
		<tr bgcolor="#E0E0E0">
			<th>Produto</th>
			<th>Descrição</th>
			<th>Preço</th>
			<th>Estoque</th>
			<th>Comprar</th>
		</tr>
		<%
			// Retira produto da sessão
			sessao.setAttribute("produto", null);

			// Busca todos os produtos e adiciona em lista	
			List<Produto> lista = new ArrayList<Produto>();
			try {
				Registry registry = LocateRegistry.getRegistry(null);
				ProdutoDAO produtoDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
				lista = (List<Produto>) produtoDAO.buscarTodos();
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}

			// Percorre lista
			for (Produto p : lista) {
				// Formatação $
				DecimalFormat df = new DecimalFormat("0.##");

				// Imprime a lista
		%>

		<tr>
			<td><%=p.getNome()%></td>
			<td><%=p.getDescricao()%></td>
			<td><%=df.format(p.getPreco_unitario())%></td>
			<td><%=p.getQtde_estoque()%></td>
			<td><a href="compra.jsp?produto=<%=p.getNome()%>">Comprar</a></td>
		</tr>
		<%
			}
		%>
	
</body>
</html>