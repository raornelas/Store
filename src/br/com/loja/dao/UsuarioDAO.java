package br.com.loja.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import br.com.loja.model.Usuario;

public interface UsuarioDAO extends Remote{	
	public void cadastrar(Usuario usuario) throws RemoteException;
	public Usuario autenticar(Usuario usuario) throws RemoteException;
	public Usuario buscarPorNome(String nome) throws RemoteException;
	public Usuario buscarPorId(int id) throws RemoteException;	
}
