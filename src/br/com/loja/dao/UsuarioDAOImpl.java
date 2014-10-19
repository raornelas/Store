package br.com.loja.dao;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.loja.model.*;
import br.com.loja.jdbc.Conecta;



/**
 * Data Access para tabela tb_usuario
 */


public class UsuarioDAOImpl extends UnicastRemoteObject implements UsuarioDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5451679340516077655L;
	Connection con;

	public UsuarioDAOImpl() throws RemoteException {
		con = Conecta.getConnection();
	}
	
	// Cadastra novo usuario
	public void cadastrar(Usuario usuario) { 

		// Comando SQL
		String sql = "insert into tb_usuario (nome, endereco, nivel, senha, usuario) values (?,?,?,?,?)";

		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, usuario.getNome());
			preparadorSQL.setString(2, usuario.getEndereco());
			preparadorSQL.setInt(3, usuario.getNivel());
			preparadorSQL.setString(4, usuario.getSenha());
			preparadorSQL.setString(5, usuario.getUsuario());
			
			// Commit no banco
			preparadorSQL.execute();
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Autentica login de usuário
	public Usuario autenticar(Usuario usuario) {

		// Comando SQL
		String sql = "select * from tb_usuario where usuario=? and senha=?";
		
		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, usuario.getUsuario());
			preparadorSQL.setString(2, usuario.getSenha());

			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();

			// Tirando do Resultset e colocando no objeto usuario
			if (res.next()) {
				 Usuario usu = new Usuario();
				usu.setId(res.getInt("id"));
				usu.setNome(res.getString("nome"));
				usu.setUsuario(res.getString("usuario"));
				usu.setSenha(res.getString("senha"));
				usu.setNivel(res.getInt("nivel"));
				usu.setEndereco(res.getString("endereco"));
				return usu;
			}

			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// Busca usuário dado nome
	public Usuario buscarPorNome(String nome) {
		// Comando SQL
		String sql = "select * from tb_usuario where nome=?";
		
		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setString(1, nome);
			
			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();

			// Tirando do Resultset e colocando no objeto usuario
			if (res.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(res.getInt("id"));
				usuario.setNome(res.getString("nome"));
				usuario.setEndereco(res.getString("endereco"));
				usuario.setNivel(res.getInt("nivel"));
				usuario.setSenha(res.getString("senha"));
				usuario.setUsuario(res.getString("usuario"));
				return usuario;
			}
			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// Busca usuário dado id
	public Usuario buscarPorId(int id) {
		
		// Comando SQL
		String sql = "select * from tb_usuario where id=?";
		
		// Statments
		try {
			PreparedStatement preparadorSQL = con.prepareStatement(sql);
			preparadorSQL.setInt(1, id);
			
			// Commit no banco
			ResultSet res = preparadorSQL.executeQuery();

			// Tirando do Resultset e colocando no objeto usuario
			if (res.next()) {

				Usuario usuario = new Usuario();
				usuario.setId(res.getInt("id"));
				usuario.setNome(res.getString("nome"));
				usuario.setEndereco(res.getString("endereco"));
				usuario.setNivel(res.getInt("nivel"));
				usuario.setSenha(res.getString("senha"));
				usuario.setUsuario(res.getString("usuario"));
				return usuario;
			}

			preparadorSQL.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    
    public static void main(String args[]) {
        
        try {
            UsuarioDAOImpl obj = new UsuarioDAOImpl();
            UsuarioDAO stub = (UsuarioDAO) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("UsuarioDAO", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }       
    }
}