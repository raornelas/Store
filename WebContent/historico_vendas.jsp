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
</head>
<body>
	<%@include file="menu.jsp"%>
	<h1>Histórico</h1>
	<br>
	<br>
	<table border="1" cellpadding="5">
		<tr bgcolor="#E0E0E0">
			<th>Produto</th>
			<th>Cliente</th>
			<th>Quantidade</th>
			<th>Valor da compra</th>
			<th>Endereço</th>
			<th>Forma de pagamento</th>
			<th>Cartão</th>

		</tr>
		<%
			
			List<Compra> lista = new ArrayList<Compra>();
			Usuario usu = new Usuario();
			Produto prod = new Produto();

			// Busca histórico de vendas 
			try {
				Registry registry = LocateRegistry.getRegistry(null);
				CompraDAO compraDAO = (CompraDAO) registry.lookup("CompraDAO");
				lista = (List<Compra>) compraDAO.buscarVendas();
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}

			// Percorre a lista de vendas
			for (Compra c : lista) {

				// Busca produto de instância de venda
				try {
					Registry registry = LocateRegistry.getRegistry(null);
					ProdutoDAO produtoDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
					prod = new Produto();
					prod = produtoDAO.buscarPorId(c.getId_produto());
				} catch (Exception e) {
					System.err.println("Client exception: " + e.toString());
					e.printStackTrace();
				}

				// Busca cliente de instancia de venda
				try {
					Registry registry = LocateRegistry.getRegistry(null);
					UsuarioDAO usuarioDAO = (UsuarioDAO) registry.lookup("UsuarioDAO");
					int id_usuario = usuarioDAO.buscarPorNome(usuario).getId();
					usu = new Usuario();
					usu = usuarioDAO.buscarPorId(c.getId_usuario());
				} catch (Exception e) {
					System.err.println("Client exception: " + e.toString());
					e.printStackTrace();
				}

				// Formatação $
				DecimalFormat df = new DecimalFormat("0.##");
		
		// Imprime a lista
		%>
		<tr>
			<td><%=prod.getNome()%></td>
			<td><%=usu.getNome()%></td>
			<td><%=c.getQuantidade()%></td>
			<td><%=df.format(c.getTotal())%></td>
			<td><%=c.getEndereco()%></td>
			<td><%=c.getForma_pagamento()%></td>
			<td><%=c.getCartao()%></td>
		</tr>
		<%
			}
		%>
	
</body>
</html>