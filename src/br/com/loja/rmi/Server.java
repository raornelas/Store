package br.com.loja.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import br.com.loja.dao.*;


public class Server
{
public static void main(String args[]) {
		
		int port = 1099;
		
		try {
			LocateRegistry.createRegistry( port ); 
		} 
		catch ( Exception e1 ) {
			e1.printStackTrace();
		}

        
        try {
            ProdutoDAOImpl obj = new ProdutoDAOImpl();

            // Bind do objeto remoto no registry
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind("ProdutoDAO", obj);           
            System.err.println("ProdutoDAO pronto.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }      
        
        try {
            UsuarioDAOImpl obj2 = new UsuarioDAOImpl();

            // Bind do objeto remoto no registry
            Registry registry2 = LocateRegistry.getRegistry(port);
            registry2.rebind("UsuarioDAO", obj2);                    
            System.err.println("UsuarioDAO pronto.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        } 
        
        try {
            CompraDAOImpl obj3 = new CompraDAOImpl();
            //CompraDAO stub3 = (CompraDAO) UnicastRemoteObject.exportObject(obj3, 2);

            // Bind do objeto remoto no registry
            Registry registry3 = LocateRegistry.getRegistry(port);
            registry3.rebind("CompraDAO", obj3);           
            System.err.println("CompraDAO pronto.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }      
    }
}