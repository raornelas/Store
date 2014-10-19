
<table border="1" cellspacing="5" cellpadding="5">
	<tr>
		<td><a href="index.jsp" />Inicio</td>
		<%
			//Fazendo um casting para poder acessar a sessao
			//HttpServletRequest httpResquest = (HttpServletRequest) request;
			HttpSession sessao = request.getSession(false);

			// Recupera usu�rio e n�vel da sess�o
			String usuario = (String) sessao.getAttribute("usuario");
			Integer nivel = (Integer) sessao.getAttribute("nivel");

			// Se usu�rio n�o estiver logado
			if (usuario == null) {
		%>

		<td><a href="login.jsp" />Login</td>
		<td><a href="registra.jsp" />Registrar</td>
		<%
			} else {
				// Se usu�rio for administrador
				if (usuario.equals("admin")) {
		%>
		<td><a href="gerencia_produtos.jsp" />Estoque</td>
		<td><a href="historico_vendas.jsp" />Hist�rico</td>
		<td><a href="logout.jsp" />Logout</td>
		<%
			} else {
					// Se for usu�rio normal
		%>
		<td><a href="historico_compras.jsp" />Hist�rico</td>
		<td><a href="logout.jsp" />Logout</td>
		<%
			}
				// Imprime nome do usu�rio
				if (usuario != null) {
		%>

		<td><%=usuario%></td>
		<%
			}

			}
		%>
	</tr>

</table>
