package br.com.loja.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com banco mysql db_loja
 */

public class Conecta {
	
	public static Connection getConnection() {
		Connection con = null;

		try {
			// Chamada do driver mysql
			Class.forName("com.mysql.jdbc.Driver");
			
			// Referencia para um objeto de conexao
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_loja",
					"rafael", "rafael");
			
			System.out.println("Conectado ao banco 'Loja'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}