package br.com.loja.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.loja.model.Compra;


public interface CompraDAO extends Remote{	
	public List<Compra> buscarPorUsuario(int id_usuario) throws RemoteException;
	public void cadastrar_venda(int id_produto, int id_usuario, String endereco, int quantidade , 
			float preco_total, String forma_pagamento, String cartao) throws RemoteException;
	public List<Compra> buscarVendas() throws RemoteException;	
}