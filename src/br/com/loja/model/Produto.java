package br.com.loja.model;

import java.io.Serializable;

/**
 * Model para Produto
 */

public final class Produto implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 8367908553994431734L;
	public int id;
	public String nome;
	public String descricao;
	public float preco_unitario;
	public int qtde_estoque;
	
	public int getQtde_estoque() {
		return qtde_estoque;
	}
	public void setQtde_estoque(int qtde_estoque) {
		this.qtde_estoque = qtde_estoque;
	}
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(float preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	
}
