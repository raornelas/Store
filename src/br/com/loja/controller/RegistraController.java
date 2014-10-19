package br.com.loja.controller;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.loja.model.Usuario;
import br.com.loja.dao.UsuarioDAO;

/**
 * Servlet Controller para registro de usuários
 */
@WebServlet("/RegistraController")
public class RegistraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RegistraController() {
        super();
        // TODO Auto-generated constructor stub
    }	

    // Chamada pelo método post (registra.jsp)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recupera dados do formulário
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		
		// Intancia de usuario
		Usuario user =  new Usuario();
		user.setUsuario(usuario);
		user.setEndereco(endereco);
		user.setNivel(0);
		user.setSenha(senha);
		user.setNome(nome);
		
		// Chamada remota para cadastrar usuário		
		try {
	    	Registry registry = LocateRegistry.getRegistry(null);
	        UsuarioDAO usuarioDAO = (UsuarioDAO) registry.lookup("UsuarioDAO");
	        
	        // Adiciona no banco
			usuarioDAO.cadastrar(user);
	            
	    } catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
	
		// Redireciona para tela de login após cadastro
		response.sendRedirect("login.jsp");
		
	}

}
