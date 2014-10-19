package br.com.loja.controller;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.loja.dao.*;
import br.com.loja.model.Usuario;

/**
 * Controller para login de usuários
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Recebe através de método post de login.jsp
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Inicia sessão
		HttpSession sessao = request.getSession();

		// Recupera dados do formulário em login.jsp
		String usuario = request.getParameter("username");
		String password = request.getParameter("password");

		// Recupera produto caso usuário tenha iniciado
		// compra antes de logar
		String produto = (String) sessao.getAttribute("produto");

		// Instancia usuario
		Usuario usu = new Usuario();
		usu.setUsuario(usuario);
		usu.setSenha(password);

		// Busca dados do usuário
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			UsuarioDAO usuarioDAO = (UsuarioDAO) registry.lookup("UsuarioDAO");

			// Autentica
			int passou = 0;
			if (usuarioDAO.autenticar(usu) != null) {

				// Se usuário for autenticado, guarda na sessão
				sessao.setAttribute("usuario", usu.getUsuario());

				// Verifica nivel de acesso do usuário
				int nivel = usu.getNivel();

				// Guarda nível de acesso na sessão
				sessao.setAttribute("nivel", nivel);

				// Se usuário não escolheu produto ainda, vai para index
				// (passou=1)
				passou = 1;

				// Se usuário já escolhei produto, vai para finalizar compra
				// (passou=3)
				if (produto != null)
					passou = 3;

			}

			// Redirecionamentos
			if (passou == 1) {
				response.sendRedirect("index.jsp");
			} else {
				if (passou == 3) {
					response.sendRedirect("compra.jsp");
				} else {
					response.sendRedirect("errologin.jsp");
				}
			}

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
