<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.loja.model.Usuario"%>
<%@page import="br.com.loja.model.Produto"%>
<%@page import="br.com.loja.model.Compra"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="br.com.loja.dao.UsuarioDAO"%>
<%@page import="br.com.loja.dao.ProdutoDAO"%>
<%@page import="br.com.loja.dao.CompraDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>


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
	<%@include file="menu.jsp"%>
	<br>
	<h2>Compras</h2>
	<table>
			<thead>
		<tr class="title">
			<th>Produto</th>
			<th>Quantidade</th>
			<th>Valor da compra</th>
			<th>Endereço</th>
			<th>Forma de pagamento</th>
			<th>Cartão</th>

		</tr>
		</thead>
		<%
			// Recupera id de usuário dado nome
			try {
				Registry registry = LocateRegistry.getRegistry(null);
				UsuarioDAO usuarioDAO = (UsuarioDAO) registry.lookup("UsuarioDAO");
				int id_usuario = usuarioDAO.buscarPorNome(usuario).getId();

				// Busca lista de compras do usuário
				List<Compra> lista = new ArrayList<Compra>();
				try {
					Registry registry2 = LocateRegistry.getRegistry(null);
					CompraDAO compraDAO = (CompraDAO) registry2.lookup("CompraDAO");
					lista = (List<Compra>) compraDAO.buscarPorUsuario(id_usuario);

					// Percorre a lista
					if (lista != null) {
						for (Compra c : lista) {
							Produto prod = new Produto();
							try {
								Registry registry3 = LocateRegistry.getRegistry(null);
								ProdutoDAO produtoDAO = (ProdutoDAO) registry3.lookup("ProdutoDAO");
								prod = new Produto();
								prod = produtoDAO.buscarPorId(c.getId_produto());
							} catch (Exception e) {
								System.err.println("Client exception: "
										+ e.toString());
								e.printStackTrace();
							}

							// Formatação $
							DecimalFormat df = new DecimalFormat("0.##");
		%>

		<tr>
			<td><%=prod.getNome()%></td>
			<td><%=c.getQuantidade()%></td>
			<td><%=df.format(c.getTotal())%></td>
			<td><%=c.getEndereco()%></td>
			<td><%=c.getForma_pagamento()%></td>
			<td><%=c.getCartao()%></td>
		</tr>
		<%
						}
					}
				} catch (Exception e) {
					System.err.println("Client exception: " + e.toString());
					e.printStackTrace();
				}

			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}
		%>
</table>	
</body>
</html>