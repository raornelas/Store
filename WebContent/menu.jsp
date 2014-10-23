<ul class="button-group">
  <li><a href="index.jsp" class="button">HOME</a></li>
  
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
		
  <li><a href="login.jsp" class="button">LOGIN</a></li>
  <li><a href="registra.jsp" class="button">CADASTRO</a></li>



		<%
			} else {
				// Se usu�rio for administrador
				if (usuario.equals("admin")) {
		%>
		 <li><a href="gerencia_produtos.jsp" class="button"/>ESTOQUE</a></li>
		 <li><a href="historico_vendas.jsp" class="button"/>HIST�RICO</a></li>
		 <li><a href="logout.jsp" class="button"/>LOGOUT</a></li>
		<%
			} else {
					// Se for usu�rio normal
		%>
		<li><a href="historico_compras.jsp" class="button"/>HIST�RICO</a></li>
		 <li><a href="logout.jsp" class="button"/>LOGOUT</a></li>

		<%
			}
				// Imprime nome do usu�rio
				if (usuario != null) {
		%>

		<li><a href="#" class="button secondary"><%=usuario%></a></li>
		<%
			}

			}
		%>
</ul>
