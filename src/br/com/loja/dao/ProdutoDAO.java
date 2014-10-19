package br.com.loja.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.com.loja.model.*;


public interface ProdutoDAO extends Remote{
	public void cadastrar(Produto produto) throws RemoteException;
	public void alterar(Produto produto) throws RemoteException;
	public void excluir(int id) throws RemoteException;
	public List<Produto> buscarTodos() throws RemoteException;
	public Produto buscarPorNome(String nome) throws RemoteException;
	public Produto buscarPorId(int id) throws RemoteException;	
	
	public Produto teste() throws RemoteException;
}