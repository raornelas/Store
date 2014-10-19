package br.com.loja.model;

import java.io.Serializable;

/**
 * Model para Usuario
 */

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 260761503428979489L;
	public int id;
	public String nome;
	public String endereco;
	public String senha;
	public String usuario;
	public int nivel;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	// Nível de acesso: [1] administrador; [0] cliente



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}

