package br.com.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.loja.model.Compra;
import br.com.loja.jdbc.Conecta;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Data Access para tabela tb_venda
 */

public class CompraDAOImpl extends UnicastRemoteObject implements CompraDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3391450382502587862L;
	private Connection con;
	private List<Compra> lista;

	public CompraDAOImpl()  throws RemoteException{
		con = Conecta.getConnection();
	}

	
	// Método busca compras feitas por usuário a partir do id
	public List<Compra> buscarPorUsuario(int id_usuario){
			
		// Instancia da lista
		List<Compra> lista =  new ArrayList<Compra>();
		
		// Comando SQL
				String sql = "select * from tb_venda where id_usuario=?";
				
				// Statments
				try {
					PreparedStatement preparadorSQL = con.prepareStatement(sql);
					preparadorSQL.setInt(1, id_usuario);
					
					// Commit no banco
					ResultSet res = preparadorSQL.executeQuery();
					
					// Tirando do Resultset e colocando no objeto produto
					while(res.next()){
						Compra compra =  new Compra();
						compra.setId(res.getInt("id") );
						compra.setQuantidade(res.getInt("quantidade") );
						compra.setEndereco(res.getString("endereco"));
						compra.setForma_pagamento(res.getString("forma_pagamento"));
						compra.setCartao(res.getString("num_cartao"));
						compra.setTotal(res.getFloat("total"));
						compra.setId_produto(res.getInt("id_produto"));
						compra.setId_usuario(res.getInt("id_usuario"));
						lista.add(compra);
					}
					preparadorSQL.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return lista;
	}
	
	// Método cadastra nova venda
	public void cadastrar_venda(int id_produto, int id_usuario, String endereco, int quantidade, 
			float preco_total, String forma_pagamento, String cartao) { 

		// Comando SQL
		String sql = "insert into tb_venda (id_produto, id_usuario, endereco, quantidade, total, forma_pagamento, num_cartao) values (?,?,?,?,?,?,?)";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setInt(1,id_produto);
			preparadorSQL.setInt(2, id_usuario);
			preparadorSQL.setString(3, endereco);
			preparadorSQL.setInt(4, quantidade);
			preparadorSQL.setFloat(5, preco_total);
			preparadorSQL.setString(6, forma_pagamento);
			preparadorSQL.setString(7, cartao);
			
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que lista todas as vendas registradas
	public List<Compra> buscarVendas() {

		// Instancia da lista
		this.lista = new ArrayList<Compra>();

		// Comando SQL
		String sql = "select * from tb_venda";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			
			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();
			
			// Tirando do Resultset e colocando no objeto produto
			while (res.next()) {
				Compra compra = new Compra();
				compra.setId(res.getInt("id"));
				compra.setQuantidade(res.getInt("quantidade"));
				compra.setEndereco(res.getString("endereco"));
				compra.setForma_pagamento(res.getString("forma_pagamento"));
				compra.setCartao(res.getString("num_cartao"));
				compra.setTotal(res.getFloat("total"));
				compra.setId_produto(res.getInt("id_produto"));
				compra.setId_usuario(res.getInt("id_usuario"));
				lista.add(compra);
			}
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
}