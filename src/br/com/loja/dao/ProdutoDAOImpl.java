package br.com.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.model.Produto;
import br.com.loja.jdbc.Conecta;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Data Access para tabela tb_produto
 */

public class ProdutoDAOImpl extends UnicastRemoteObject implements ProdutoDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4568283318145255012L;
	/**
	 * 
	 */
	private Connection con;
	Produto prod =  new Produto();
	
	public ProdutoDAOImpl() throws RemoteException {
		con = Conecta.getConnection();

	}

	// Cadastra novo produto
	public void cadastrar(Produto produto) { 

		// Comando SQL
		String sql = "insert into tb_produto (nome, descricao, quantidade,preco) values (?,?,?,?)";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, produto.getNome());
			preparadorSQL.setString(2, produto.getDescricao());
			preparadorSQL.setInt(3, produto.getQtde_estoque());
			preparadorSQL.setDouble(4, produto.getPreco_unitario());
			
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Altera pro
	public void alterar(Produto produto) { //

		// Comando SQL
		String sql = "update tb_produto set nome=?, descricao=?, quantidade=?, preco=? where id=?";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, produto.getNome());
			preparadorSQL.setString(2, produto.getDescricao());
			preparadorSQL.setInt(3, produto.getQtde_estoque());
			preparadorSQL.setDouble(4, produto.getPreco_unitario());
			preparadorSQL.setInt(5, produto.getId());
			
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Exclui produto (estoque = 0)
	// Produto não é excluído do banco pois é chave estrangeira de outra tabela
	public void excluir(int id) { //

		// Comando SQL
		String sql = "update tb_produto set quantidade=? where id=?";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setInt(1,0);
			preparadorSQL.setInt(2,id);
			
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	// Lista todos os produtos
	public List<Produto> buscarTodos(){
			
		// Instancia da lista
		List<Produto> lista =  new ArrayList<Produto>();
		
		// Comando SQL
				String sql = "select * from tb_produto";
				// Statments
				try {
					PreparedStatement preparadorSQL = con.prepareStatement(sql);
					
					// Commit no banco
					ResultSet res = preparadorSQL.executeQuery();
					
					//Tirando do Resultset e colocando no objeto produto
					while(res.next()){
						Produto produto =  new Produto();
						produto.setId(res.getInt("id") );
						produto.setNome(res.getString("nome") );
						produto.setDescricao(res.getString("descricao"));
						produto.setQtde_estoque(res.getInt("quantidade"));
						produto.setPreco_unitario(res.getFloat("preco"));
						lista.add(produto);
					}
					preparadorSQL.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return lista;
	}
	
	//TESTE
	
	public Produto teste(){

		//Produto produto =  new Produto();
		
		prod.setNome("Teste 3!");		
		return prod;
		
	}
	
	
	// Busca produto dado novo
	public Produto buscarPorNome(String nome) {
		// Comando SQL
		String sql = "select * from tb_produto where nome=?";
		
		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, nome);
			
			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();

			// Tirando do Resultset e colocando no objeto usuario
			if (res.next()) {
				Produto produto = new Produto();
				produto.setId(res.getInt("id"));
				produto.setNome(res.getString("nome"));
				produto.setPreco_unitario(res.getFloat("preco"));
				produto.setDescricao(res.getString("descricao"));
				produto.setQtde_estoque(res.getInt("quantidade"));
				return produto;
			}
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// Busca produto dado id
	public Produto buscarPorId(int id) {
		
		// Comando SQL
		String sql = "select * from tb_produto where id=?";
		
		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setInt(1, id);
			
			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();

			// Tirando do Resultset e colocando no objeto usuario
			if (res.next()) {

				Produto produto = new Produto();
				produto.setId(res.getInt("id"));
				produto.setNome(res.getString("nome"));
				produto.setPreco_unitario(res.getFloat("preco"));
				produto.setDescricao(res.getString("descricao"));
				produto.setQtde_estoque(res.getInt("quantidade"));
				return produto;
			}

			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	public static void main(String args[]) {
		
		int port = 1099;

        
        try {
            ProdutoDAOImpl obj = new ProdutoDAOImpl();
            ProdutoDAO stub = (ProdutoDAO) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind("ProdutoDAO", stub);
            
            
            
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }       
    }
    */
}