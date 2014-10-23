<ul class="button-group">
  <li><a href="index.jsp" class="button">HOME</a></li>
  
  <%
			//Fazendo um casting para poder acessar a sessao
			//HttpServletRequest httpResquest = (HttpServletRequest) request;
			HttpSession sessao = request.getSession(false);

			// Recupera usuário e nível da sessão
			String usuario = (String) sessao.getAttribute("usuario");
			Integer nivel = (Integer) sessao.getAttribute("nivel");

			// Se usuário não estiver logado
			if (usuario == null) {
	%>
		
  <li><a href="login.jsp" class="button">LOGIN</a></li>
  <li><a href="registra.jsp" class="button">CADASTRO</a></li>



		<%
			} else {
				// Se usuário for administrador
				if (usuario.equals("admin")) {
		%>
		 <li><a href="gerencia_produtos.jsp" class="button"/>ESTOQUE</a></li>
		 <li><a href="historico_vendas.jsp" class="button"/>HISTÓRICO</a></li>
		 <li><a href="logout.jsp" class="button"/>LOGOUT</a></li>
		<%
			} else {
					// Se for usuário normal
		%>
		<li><a href="historico_compras.jsp" class="button"/>HISTÓRICO</a></li>
		 <li><a href="logout.jsp" class="button"/>LOGOUT</a></li>

		<%
			}
				// Imprime nome do usuário
				if (usuario != null) {
		%>

		<li><a href="#" class="button secondary"><%=usuario%></a></li>
		<%
			}

			}
		%>
</ul>
