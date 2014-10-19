package br.com.loja.controller;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import br.com.loja.dao.*;
import br.com.loja.model.*;
/**
 * Controller para a��es de Compra
 */
@WebServlet("/CompraController")
public class CompraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CompraController() {
        super();
        
    }

    // Recebe por metodo post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recupera usuario e produto na sess�o
		HttpSession sessao = request.getSession(false);
		String produto = (String) sessao.getAttribute("produto");
		String usuario = (String) sessao.getAttribute("usuario");
		
//		sessao.setAttribute("produto",produto);	
		
			
		// Recupera dados do formul�rio compra.jsp
		String quantidade = request.getParameter("quantidade");
		String pagamento = request.getParameter("pagamento");
		String cartao = request.getParameter("cartao");
		String endereco = request.getParameter("endereco");

		// Se usu�rio ainda n�o estiver logado, salva dados na sess�o
		// para utilizar ap�s o login
		if(usuario==null){		
			sessao.setAttribute("quantidade",quantidade);
			sessao.setAttribute("pagamento",pagamento);
			sessao.setAttribute("cartao",cartao);
			sessao.setAttribute("endereco",endereco);
			
			// Redireciona usu�rio para tela de login
			String nextJSP = "/login.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		
		// Altera quantidade como int
		int qtde = Integer.parseInt(quantidade);
		
		// Instancia usuario
		Usuario user = new Usuario();
		
		int id = 0;
		// Busca dados do usu�rio
		try {
			Registry registry = LocateRegistry.getRegistry(null);
			UsuarioDAO usuarioDAO = (UsuarioDAO) registry.lookup("UsuarioDAO");
			
			// Busca id do usuario a partir do nome
			user = usuarioDAO.buscarPorNome(usuario);
			int id_usuario = user.getId();

			id = id_usuario;
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

		// Busca dados do produto		
		try {
	    	Registry registry = LocateRegistry.getRegistry(null);
	        ProdutoDAO produtoDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
	        
	        // Busca id do produto a partir do nome
			int id_produto = produtoDAO.buscarPorNome(produto).getId();
			
			// Calcula pre�o total da compra
			float preco_total = (float) (produtoDAO.buscarPorNome(produto).getPreco_unitario() * qtde); 
			
			// Verifica se ha quantidade disponivel em estoque
			Produto prod = new Produto();
			prod = produtoDAO.buscarPorNome(produto);
			
			// Busca dados da compra
			try {
		    	Registry registry2 = LocateRegistry.getRegistry(null);
		        CompraDAO compraDAO = (CompraDAO) registry2.lookup("CompraDAO");
		        
				// Grava no banco
				compraDAO.cadastrar_venda(id_produto, id, endereco, qtde, preco_total, pagamento, cartao); 
				
				// Reduz do estoque
				prod.setQtde_estoque(prod.getQtde_estoque() - qtde);
				produtoDAO.alterar(prod);
				
				
		    } catch (Exception e) {
		        System.err.println("Client exception: " + e.toString());
		        e.printStackTrace();
		    }
			
	    } catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
		
		

		// Desliga o produto da sess�o
		sessao.setAttribute("produto",null);
		
		// Redireciona para tela ap�s compra bem sucedida
		response.sendRedirect("compra_sucesso.jsp");
		
		
	}

}
