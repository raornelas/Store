package br.com.loja.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.loja.dao.*;
import br.com.loja.model.Produto;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



/**
 * Servlet Controller de ações de produtos
 */
@WebServlet("/ProdutoController")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProdutoController() {
        super();
        
    }

    // Se veio pelo método get (gerencia_produtos.jsp ou altera_produto.jsp)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	
    	// Recupera acao quando vem por gerencia_produtos.jsp
    	String acao;
    	acao = request.getParameter("acao");
    	
    	// Recupera produto em questão
    	String produto = request.getParameter("produto");
    	
    	// Inicia variaveis para id do produto
    	int prod = 0;
    	int id_produto = 0;
    	
    	// Recupera sessão
		HttpSession sessao = request.getSession(false);
		
		// Se acao nao foi recuperado ainda, entao veio por sessao de altera_produto.jsp
		if(acao==null){
			acao = (String) sessao.getAttribute("acao");
		}
		
		// Se produto nao foi recuperado ainda, entao veio por sessao de altera_produto.jsp
     	if(produto==null) {
     		prod = (int) sessao.getAttribute("id_prod");
     	}
     	
     	// Se produto ja foi recuperado, transforma id em inteiro
    	if(produto!=null) {
    	 	id_produto = Integer.parseInt(produto);
    	}
    	
    	// Instancia DAO para produto
    	//ProdutoDAO pDAO = new ProdutoDAO();
    	
    	// Se a ação a tomar é exluir produto
    	if (acao.equals("excluir")){   		
    		try {
    	    	Registry registry = LocateRegistry.getRegistry(null);
    	        ProdutoDAO pDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
    	        
        		// Chama do método excluir produto
        		pDAO.excluir(id_produto); 
        		
    	    } catch (Exception e) {
    	        System.err.println("Client exception: " + e.toString());
    	        e.printStackTrace();
    	    }
    	
    		// Redireciona para tela de estoque
    		response.sendRedirect("gerencia_produtos.jsp");
    	}
    	
    	// Se a ação a tomar é alterar os dados do produto
    	if(acao.equals("altera")){
    	
    		// Instancia produto e seta atributos
    		Produto p = new Produto();
    		p.setId(prod);
    		p.setNome(request.getParameter("nome"));	
    		p.setQtde_estoque(Integer.parseInt(request.getParameter("quantidade")));  		
    		p.setDescricao(request.getParameter("descricao"));
    		p.setPreco_unitario(Float.parseFloat(request.getParameter("preco")));
    		
    		// Chamada remota para alterar o banco
    		try {
    	    	Registry registry = LocateRegistry.getRegistry(null);
    	        ProdutoDAO pDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
    	        
        		// Chama método para update na tabela
        		pDAO.alterar(p);
    	            
    	    } catch (Exception e) {
    	        System.err.println("Client exception: " + e.toString());
    	        e.printStackTrace();
    	    }
    		

    		
    		// Redireciona usuário para tela de estoque
    		response.sendRedirect("gerencia_produtos.jsp");
    		
    	}
    }
	
    // Se for chamado por método post (adicionaproduto.jsp)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recupera dados do formulário
		String nome = request.getParameter("nome");
		int quantidade  = Integer.parseInt(request.getParameter("quantidade"));
		float preco = Float.parseFloat(request.getParameter("preco"));
		String descricao = request.getParameter("descricao");
				
		// Intancia de usuario
		Produto prod = new Produto();
		prod.setNome(nome);
		prod.setQtde_estoque(quantidade);
		prod.setDescricao(descricao);
		prod.setPreco_unitario(preco);
		

		// Chamada remoto para adicionar produto no banco
		try {
	    	Registry registry = LocateRegistry.getRegistry(null);
	        ProdutoDAO produtoDAO = (ProdutoDAO) registry.lookup("ProdutoDAO");
	        
	        // Adiciona no banco
			produtoDAO.cadastrar(prod);
		
	    } catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }

		

		// Redireciona usuário para tela de estoque
		response.sendRedirect("gerencia_produtos.jsp");
	}

}
